package com.gilxyj.easyspring.aop.framework;

import com.gilxyj.easyspring.aop.Advice;
import com.gilxyj.easyspring.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.*;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: easyspring
 * @description:
 * @作者 飞码录
 * @微信公众号 飞码录
 * @网站 http://www.codesboy.cn
 * @国际站 http://www.codesboy.com
 * @微信 gilbertxy
 * @GitHub https://github.com/GilbertXiao
 * @Gitee https://gitee.com/gilbertxiao
 * @create: 2020-09-21 01:10
 **/
public class CglibProxyFactory implements AopProxyFactory{

    // Constants for CGLIB callback array indices
    private static final int AOP_PROXY = 0;
    private static final int INVOKE_TARGET = 1;
    private static final int NO_OVERRIDE = 2;
    private static final int DISPATCH_TARGET = 3;
    private static final int DISPATCH_ADVISED = 4;
    private static final int INVOKE_EQUALS = 5;
    private static final int INVOKE_HASHCODE = 6;

    private static final Logger LOGGER= LoggerFactory.getLogger(CglibProxyFactory.class);

    protected final AopConfig config;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;


    public CglibProxyFactory(AopConfig config) {
        Assert.notNull(config, "AdvisedSupport must not be null");
        if (config.getAdvices().size() == 0) {
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        this.config = config;

    }

    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating CGLIB proxy: target source is " + this.config.getTargetClass());
        }

        Class<?> rootClass = this.config.getTargetClass();

        Enhancer enhancer = new Enhancer();
        if (classLoader != null) {
            enhancer.setClassLoader(classLoader);
        }
        enhancer.setSuperclass(rootClass);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);//"BySpringCGLIB"
        enhancer.setInterceptDuringConstruction(false);

        Callback[] callbacks = null;
        try {
            callbacks = getCallbacks(rootClass);
        } catch (Exception e) {
            throw new AopConfigException("Unexpected AOP exception", e);
        }
        Class<?>[] types = new Class<?>[callbacks.length];

        for (int i = 0; i < types.length; i++) {
            types[i] = callbacks[i].getClass();
        }

        enhancer.setCallbackFilter(new ProxyCallbackFilter(this.config));
        enhancer.setCallbackTypes(types);
        enhancer.setCallbacks(callbacks);
        Object proxy = enhancer.create();
        return proxy;
    }

    private Callback[] getCallbacks(Class<?> rootClass) throws Exception {
        Callback aopInterceptor = new DynamicAdvicesInterceptor(this.config);
        Callback[] callbacks = new Callback[]{aopInterceptor};
        return callbacks;
    }

    /**
     * General purpose AOP callback. Used when the target is dynamic or when the
     * proxy is not frozen.
     */
    private static class DynamicAdvicesInterceptor implements MethodInterceptor, Serializable{

        private final AopConfig aopConfig;

        public DynamicAdvicesInterceptor(AopConfig aopConfig) {
            this.aopConfig = aopConfig;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Object targetObject = this.aopConfig.getTargetObject();//e.g. petService#placeOrder()
            List<Advice> chain = this.aopConfig.getAdvices(method);

            Object retVal;
            // Check whether we only have one InvokerInterceptor: that is,
            // no real advice, but just reflective invocation of the target.
            if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) {
                // We can skip creating a MethodInvocation: just invoke the target directly.
                // Note that the final invoker must be an InvokerInterceptor, so we know
                // it does nothing but a reflective operation on the target, and no hot
                // swapping or fancy proxying.
                retVal = methodProxy.invoke(targetObject, objects);
            } else {
                List<org.aopalliance.intercept.MethodInterceptor> interceptors = new ArrayList<>();
                interceptors.addAll(chain);
                // We need to create a method invocation...
                retVal = new ReflectiveMethodInvocation(targetObject, method, objects, interceptors).proceed();
            }

            return retVal;
        }
    }

    private static class ProxyCallbackFilter implements CallbackFilter{
        private final AopConfig config;

        public ProxyCallbackFilter(AopConfig config) {
            this.config = config;
        }

        @Override
        public int accept(Method method) {
            return AOP_PROXY;
        }

    }
}
