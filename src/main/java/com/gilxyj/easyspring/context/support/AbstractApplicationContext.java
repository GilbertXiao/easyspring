package com.gilxyj.easyspring.context.support;

import com.gilxyj.easyspring.beans.factory.annotation.AutowiredAnnotationProcessor;
import com.gilxyj.easyspring.beans.factory.config.AutowireCapableBeanFatory;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.util.ClassUtils;

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
 * @create: 2020-08-23 20:47
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        Resource resource = this.getResource(configFile);
        reader.loadBeanDefinition(resource);
        defaultBeanFactory.setBeanCLassLoader(this.getBeanClassLoader());
        registerBeanPostProcessors(defaultBeanFactory);
    }

    abstract Resource getResource(String configFile) ;

    @Override
    public Object getBean(String beanID) {
        return defaultBeanFactory.getBean(beanID);
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

    protected void registerBeanPostProcessors(AutowireCapableBeanFatory beanFatory){
        AutowiredAnnotationProcessor autowiredAnnotationProcessor = new AutowiredAnnotationProcessor();
        autowiredAnnotationProcessor.setBeanFactory(beanFatory);
        beanFatory.addBeanPostProcessor(autowiredAnnotationProcessor);

    }
}
