package com.gilxyj.easyspring.beans.factory.support;

import com.gilxyj.easyspring.beans.BeanDefinition;

import com.gilxyj.easyspring.beans.factory.BeanCreationException;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.factory.BeanFactory;
import com.gilxyj.easyspring.beans.factory.config.ConfigurableBeanFactory;
import com.gilxyj.easyspring.beans.support.BeanDefinitionRegistry;
import com.gilxyj.easyspring.beans.support.GenericBeanDefinition;
import com.gilxyj.easyspring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
 * @create: 2020-08-22 23:38
 **/
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {


    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    private ClassLoader beanClassLoader;

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return this.beanDefinitionMap.get(id);
    }

    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanID, bd);
    }

    @Override
    public Object getBean(String beanID) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanID);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanID);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(beanID,bean);
            }
            return bean;
        }
        return createBean(beanDefinition);

    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader cl = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> aClass = cl.loadClass(beanClassName);
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }

    @Override
    public void setBeanCLassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        if (this.beanClassLoader != null) {
            return this.beanClassLoader;
        }
        return ClassUtils.getDefaultClassLoader();
    }
}
