package com.gilxyj.easyspring.beans;

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
 * @create: 2020-08-22 23:44
 **/
public interface BeanDefinition {

    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";

    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    ConstructorArgument getConstructorArgument();

    boolean hasConstructorArgumentValues();

    String getScope();

    void setScope(String scope);

    List<PropertyValue> getPropertyValues();
    String getId();

    Class<?> getBeanClass();

    boolean hasBeanClass();

    Class<?> resolveBeanClass(ClassLoader beanClassLoader) throws ClassNotFoundException;
}
