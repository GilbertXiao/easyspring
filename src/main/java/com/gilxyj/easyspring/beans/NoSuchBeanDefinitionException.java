package com.gilxyj.easyspring.beans;

import com.gilxyj.easyspring.util.StringUtils;

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
 * @create: 2020-09-19 16:42
 **/
public class NoSuchBeanDefinitionException extends BeansException {

    private String beanName;

    private Class<?> beanType;

    public NoSuchBeanDefinitionException(String name) {
        super("No bean named '" + name + "' is defined");
        this.beanName = name;
    }

    public NoSuchBeanDefinitionException(String name,String message) {
        super("No bean named '" + name + "' is defined:"+message);
        this.beanName = name;
    }

    public NoSuchBeanDefinitionException(Class<?> beanType) {
        super("No qualifying bean of type [" + beanType.getName() + "] is defined");
        this.beanType = beanType;
    }

    public NoSuchBeanDefinitionException(Class<?> beanType,String message) {
        super("No qualifying bean of type [" + beanType.getName() + "] is defined:"+message);
        this.beanType = beanType;
    }

    public NoSuchBeanDefinitionException(Class<?> beanType,String dependencyDescription,String message) {
        super("No qualifying bean of type [" + beanType.getName() + "] found for dependency" + (StringUtils.hasLength(dependencyDescription) ? "[" + dependencyDescription + "]" : "") + ": " + message);
        this.beanType = beanType;
    }

    public String getBeanName() {
        return beanName;
    }

    public Class<?> getBeanType() {
        return beanType;
    }
}
