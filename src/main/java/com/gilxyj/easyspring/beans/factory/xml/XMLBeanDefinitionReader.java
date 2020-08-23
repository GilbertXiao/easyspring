package com.gilxyj.easyspring.beans.factory.xml;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.support.BeanDefinitionRegistry;
import com.gilxyj.easyspring.beans.support.GenericBeanDefinition;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

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
 * @create: 2020-08-23 15:35
 **/
public class XMLBeanDefinitionReader {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry registry;

    public XMLBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinition(Resource resource) {


        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try(InputStream is = resource.getInputStream()) {
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
            String scope = element.attributeValue(SCOPE_ATTRIBUTE);
            if (scope != null) {
                bd.setScope(scope);
            }
            this.registry.registerBeanDefinition(id, bd);
        }



    }
}
