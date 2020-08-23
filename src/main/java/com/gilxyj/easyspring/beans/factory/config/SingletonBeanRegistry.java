package com.gilxyj.easyspring.beans.factory.config;

public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object object);

    Object getSingleton(String beanName);
}
