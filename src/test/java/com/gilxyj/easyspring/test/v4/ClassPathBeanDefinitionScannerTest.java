package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.context.annotation.ClassPathBeanDefinitionScanner;
import com.gilxyj.easyspring.context.annotation.ScannedGenericBeanDefinition;
import com.gilxyj.easyspring.core.annotation.AnnotationAttributes;
import com.gilxyj.easyspring.core.type.AnnotationMetadata;
import com.gilxyj.easyspring.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
 * @create: 2020-09-06 20:39
 **/
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScanedBean(){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();

        String basePackages = "com.gilxyj.easyspring.service.v4,com.gilxyj.easyspring.dao.v4";

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(defaultBeanFactory);
        scanner.doScan(basePackages);

        String annotation = Component.class.getName();

        {
            BeanDefinition bd = defaultBeanFactory.getBeanDefinition("petStoreServiceV4");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();

            assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            assertEquals("petStoreServiceV4", attributes.get("value"));


        }

        {
            BeanDefinition bd = defaultBeanFactory.getBeanDefinition("accountV4Dao");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();

            assertTrue(amd.hasAnnotation(annotation));
        }

        {
            BeanDefinition bd = defaultBeanFactory.getBeanDefinition("itemV4Dao");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();

            assertTrue(amd.hasAnnotation(annotation));
        }

    }
}
