package com.gilxyj.easyspring.beans.factory.config;

import com.gilxyj.easyspring.beans.factory.BeanFactory;

import java.util.List;

public interface AutowireCapableBeanFatory extends ConfigurableBeanFactory {
    public Object resolveDependency(DependencyDescriptor descriptor);
    void addBeanPostProcessor(BeanPostProcessor postProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();
}
