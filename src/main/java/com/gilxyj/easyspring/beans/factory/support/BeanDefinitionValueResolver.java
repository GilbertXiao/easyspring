package com.gilxyj.easyspring.beans.factory.support;

import com.gilxyj.easyspring.beans.factory.BeanFactory;
import com.gilxyj.easyspring.beans.factory.config.RuntimeBeanReference;
import com.gilxyj.easyspring.beans.factory.config.TypedStringValue;

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
 * @create: 2020-08-25 01:45
 **/
public class BeanDefinitionValueResolver {

    private final BeanFactory beanFactory;


    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public Object resolveValueIfNeccessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            String refName = ((RuntimeBeanReference) value).getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;
        }
        if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        }

        //TODO
        throw new RuntimeException("the value " + value +" has not implemented");
    }
}
