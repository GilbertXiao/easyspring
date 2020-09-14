package com.gilxyj.easyspring.beans.factory.annotation;

import com.gilxyj.easyspring.beans.BeansException;
import com.gilxyj.easyspring.beans.factory.config.AutowireCapableBeanFatory;
import com.gilxyj.easyspring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.gilxyj.easyspring.core.annotation.AnnotationUtils;
import com.gilxyj.easyspring.util.ReflectionUtils;


import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

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
 * @create: 2020-09-13 22:59
 **/
public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {
    private AutowireCapableBeanFatory beanFactory;
    private String requiredParameterName = "required";
    private boolean requiredParameterValue = true;

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();

    public AutowiredAnnotationProcessor() {
        this.autowiredAnnotationTypes.add(Autowired.class);
    }

    public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {
        LinkedList<InjectionElement> elements = new LinkedList<>();
        Class<?> targetClass = clazz;

        do {
            LinkedList<InjectionElement> currElements = new LinkedList<>();
            for (Field field : targetClass.getDeclaredFields()) {
                Annotation ann = findAutowiredAnnotation(field);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    boolean required = determineRequiredStatus(ann);
                    currElements.add(new AutowiredFieldElement(field, required, beanFactory));
                }
            }
            for (Method method : targetClass.getDeclaredMethods()) {
                //TODO for method
            }
            elements.addAll(0, currElements);
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);
        return new InjectionMetadata(clazz, elements);
    }

    private boolean determineRequiredStatus(Annotation ann) {
        Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
        if (method == null) {
            // Annotations like @Inject and @Value don't have a method (attribute) named "required"
            // -> default to required status
            return true;
        }
        return this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann);
    }

    private Annotation findAutowiredAnnotation(AccessibleObject accessibleObject) {
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            Annotation annotation = AnnotationUtils.getAnnotation(accessibleObject, type);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }

    public AutowireCapableBeanFatory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AutowireCapableBeanFatory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object beforeInstantiation(Class beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
        //do nothing
        return true;
    }

    @Override
    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        InjectionMetadata injectionMetadata = buildAutowiringMetadata(bean.getClass());
        injectionMetadata.inject(bean);
    }

    @Override
    public Object beforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object afterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
