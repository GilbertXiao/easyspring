package com.gilxyj.easyspring.beans.factory.support;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.BeanCreationException;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.factory.BeanFactory;
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
public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile) {

        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try(InputStream is = cl.getResourceAsStream(configFile)) {
            doc = saxReader.read(is);
        } catch (DocumentException | IOException e) {
            throw new BeanDefinitionStoreException("parsing XML doc failed", e);
        }
        Element rootElement = doc.getRootElement();
        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            String id = element.attributeValue(ID_ATTRIBUTE);
            String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);

            BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
            this.beanDefinitionMap.put(id, bd);


        }



    }


    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return this.beanDefinitionMap.get(id);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> aClass = cl.loadClass(beanClassName);
            return aClass.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }

    }
}
