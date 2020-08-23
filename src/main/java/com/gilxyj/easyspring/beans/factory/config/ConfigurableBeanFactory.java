package com.gilxyj.easyspring.beans.factory.config;

import com.gilxyj.easyspring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanCLassLoader(ClassLoader classLoader);
    ClassLoader getBeanClassLoader();
}
