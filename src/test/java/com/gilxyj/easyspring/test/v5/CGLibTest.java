package com.gilxyj.easyspring.test.v5;

import com.gilxyj.easyspring.service.v5.PetStoreServiceV5;
import com.gilxyj.easyspring.tx.TransactionManager;
import org.aspectj.weaver.ast.Var;
import org.junit.Test;
import org.springframework.cglib.proxy.*;

import javax.swing.*;
import java.lang.reflect.Method;

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
 * @create: 2020-09-20 22:39
 **/
public class CGLibTest {

    @Test
    public void testCallback() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(PetStoreServiceV5.class);

        enhancer.setCallback(new TransactionInterceptor());

        PetStoreServiceV5 petStoreServiceV5 = (PetStoreServiceV5) enhancer.create();
        petStoreServiceV5.placeOrder();
        petStoreServiceV5.toString();


    }

    public static class TransactionInterceptor implements MethodInterceptor {

        TransactionManager txManager = new TransactionManager();

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            txManager.start();
            Object result = methodProxy.invokeSuper(o, objects);
            txManager.commit();
            return result;
        }
    }

    @Test
    public void testFilter(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PetStoreServiceV5.class);
        //构造函数中调用的方法不会被拦截
        enhancer.setInterceptDuringConstruction(false);

        Callback[] callbacks = new Callback[]{new TransactionInterceptor(), NoOp.INSTANCE};

        Class<?>[] types = new Class<?>[callbacks.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = callbacks[i].getClass();
        }

        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);
        enhancer.setCallbackFilter(new ProxyCallbackFilter());

        PetStoreServiceV5 petStoreServiceV5 = (PetStoreServiceV5) enhancer.create();
        petStoreServiceV5.placeOrder();
        petStoreServiceV5.toString();
    }

    private static class ProxyCallbackFilter implements CallbackFilter{

        public ProxyCallbackFilter() {
        }

        @Override
        public int accept(Method method) {
            if (method.getName().startsWith("place")) {
                return 0;
            }
            return 1;
        }
    }
}
