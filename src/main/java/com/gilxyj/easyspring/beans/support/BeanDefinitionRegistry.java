package com.gilxyj.easyspring.beans.support;

import com.gilxyj.easyspring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String petStore);

    void registerBeanDefinition(String beanID, BeanDefinition bd);
}
