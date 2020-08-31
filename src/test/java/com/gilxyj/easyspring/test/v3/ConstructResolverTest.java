package com.gilxyj.easyspring.test.v3;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.support.ConstructResolver;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;

import com.gilxyj.easyspring.dao.v3.AccountDao;
import com.gilxyj.easyspring.service.v3.PetStoreServiceV3;
import org.junit.Assert;
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
 * @create: 2020-08-29 16:47
 **/
public class ConstructResolverTest {


    @Test
    public void testAutowireConstructor(){
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition bd = beanFactory.getBeanDefinition("petStoreV3");

        ConstructResolver resolver=  new ConstructResolver(beanFactory);

        PetStoreServiceV3 petStoreServiceV3= (PetStoreServiceV3) resolver.autowireConstructor(bd);

        assertEquals(1, petStoreServiceV3.getVersion());
        assertNotNull(petStoreServiceV3.getAccountDao());
        assertNotNull(petStoreServiceV3.getItemDao());
        assertTrue(petStoreServiceV3.getAccountDao() instanceof AccountDao);

    }
}
