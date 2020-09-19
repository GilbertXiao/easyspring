package com.gilxyj.easyspring.test.v5;

import com.gilxyj.easyspring.aop.config.MethodLocatingFactory;
import com.gilxyj.easyspring.beans.factory.BeanFactory;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.tx.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

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
 * @create: 2020-09-18 12:37
 **/
public class MethodLocatingFactoryTest {


    @Test
    public void testGetMethod() throws NoSuchMethodException {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource("petstore-v5.xml");
        reader.loadBeanDefinition(resource);

        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setTargetBeanName("tx");
        methodLocatingFactory.setMethodName("start");
        methodLocatingFactory.setBeanFactory(defaultBeanFactory);

        Method m = methodLocatingFactory.getObject();

        assertTrue(TransactionManager.class.equals(m.getDeclaringClass()));
        assertTrue(m.equals(TransactionManager.class.getMethod("start")));



    }
}
