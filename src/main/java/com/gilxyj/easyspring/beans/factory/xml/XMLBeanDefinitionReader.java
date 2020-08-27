package com.gilxyj.easyspring.beans.factory.xml;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.PropertyValue;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.factory.config.RuntimeBeanReference;
import com.gilxyj.easyspring.beans.factory.config.TypedStringValue;
import com.gilxyj.easyspring.beans.support.BeanDefinitionRegistry;
import com.gilxyj.easyspring.beans.support.GenericBeanDefinition;
import com.gilxyj.easyspring.core.io.Resource;


import com.gilxyj.easyspring.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public static final String PROPERTY_ATTRIBUTE = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String NAME_ATTRIBUTE = "name";


    private BeanDefinitionRegistry registry;

    private static final Logger LOGGER= LoggerFactory.getLogger(XMLBeanDefinitionReader.class);

    public XMLBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinition(Resource resource) {


        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try (InputStream is = resource.getInputStream()) {
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
            BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
            String scope = element.attributeValue(SCOPE_ATTRIBUTE);
            if (scope != null) {
                bd.setScope(scope);
            }
            parsePropertyElement(element,bd);
            this.registry.registerBeanDefinition(id, bd);
        }


    }

    public void parsePropertyElement(Element ele, BeanDefinition beanDefinition) {
        Iterator<Element> iter = ele.elementIterator(PROPERTY_ATTRIBUTE);
        while (iter.hasNext()) {
            Element propElem = iter.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                LOGGER.error("Tag 'property' must have a 'name' attribute");
                return;
            }

            Object value = parsePropertyValue(beanDefinition, propElem, propertyName);

            PropertyValue propertyValue = new PropertyValue(propertyName, value);
            beanDefinition.getPropertyValues().add(propertyValue);

        }
    }

    private Object parsePropertyValue(BeanDefinition beanDefinition, Element propElem, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";
        boolean hasRefAttr = propElem.attribute(REF_ATTRIBUTE) != null;
        boolean hasValueAttr = propElem.attribute(VALUE_ATTRIBUTE) != null;
        if (hasRefAttr) {
            String refName = propElem.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                LOGGER.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(refName);
            return runtimeBeanReference;
        }
        if (hasValueAttr) {
            String value = propElem.attributeValue(VALUE_ATTRIBUTE);
            TypedStringValue typeStringValue = new TypedStringValue(value);
            return typeStringValue;
        }
        throw new RuntimeException(elementName +  " must specify a ref or value");
    }
}
