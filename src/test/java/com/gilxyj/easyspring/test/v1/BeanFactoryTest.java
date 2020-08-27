package com.gilxyj.easyspring.test.v1;



import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.BeanCreationException;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.factory.BeanFactory;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.service.v1.PetStoreService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    private DefaultBeanFactory beanFactory;
    private XMLBeanDefinitionReader reader ;

    @Before
    public void setUp(){
         beanFactory = new DefaultBeanFactory();
         reader = new XMLBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean(){

        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition beanDefinition= beanFactory.getBeanDefinition("petStore");

        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());

        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        assertEquals("com.gilxyj.easyspring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);

        PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStore");
        assertTrue(petStoreService.equals(petStoreService1));
    }

    @Test(expected = BeanCreationException.class)
    public void testInvalidBean(){
        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        Object invalidBean = beanFactory.getBean("invalidBean");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInvalidXML(){
        reader.loadBeanDefinition(new ClassPathResource("xxx.xml"));
    }

    @Test
    public void testPrototypeBean(){

        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition beanDefinition= beanFactory.getBeanDefinition("petStore");

        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());

        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        assertEquals("com.gilxyj.easyspring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);

        PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStore");
        assertTrue(petStoreService.equals(petStoreService1));
    }
}
