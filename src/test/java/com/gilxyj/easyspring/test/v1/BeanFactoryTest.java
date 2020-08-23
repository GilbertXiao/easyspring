package com.gilxyj.easyspring.test.v1;



import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.BeanCreationException;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.factory.BeanFactory;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.service.v1.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
 * @create: 2020-08-22 23:20
 **/
public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition= beanFactory.getBeanDefinition("petStore");

        assertEquals("com.gilxyj.easyspring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test(expected = BeanCreationException.class)
    public void testInvalidBean(){
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        Object invalidBean = beanFactory.getBean("invalidBean");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInvalidXML(){
        BeanFactory beanFactory = new DefaultBeanFactory("xxx.xml");
    }
}
