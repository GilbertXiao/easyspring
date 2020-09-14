package com.gilxyj.easyspring.beans.factory.config;

import com.gilxyj.easyspring.beans.BeansException;

public interface BeanPostProcessor {

    Object beforeInitialization(Object bean,String beanName) throws BeansException;

    Object afterInitialization(Object bean, String beanName) throws BeansException;
}
