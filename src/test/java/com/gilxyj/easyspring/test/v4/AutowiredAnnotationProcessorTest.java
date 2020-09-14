package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.beans.factory.annotation.AutowiredAnnotationProcessor;
import com.gilxyj.easyspring.beans.factory.annotation.AutowiredFieldElement;
import com.gilxyj.easyspring.beans.factory.annotation.InjectionElement;
import com.gilxyj.easyspring.beans.factory.annotation.InjectionMetadata;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.dao.v4.AccountV4Dao;
import com.gilxyj.easyspring.dao.v4.ItemV4Dao;
import com.gilxyj.easyspring.service.v4.PetStoreServiceV4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

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
 * @create: 2020-09-13 21:47
 **/
public class AutowiredAnnotationProcessorTest {

    AccountV4Dao accountV4Dao;
    ItemV4Dao itemV4Dao;
    DefaultBeanFactory defaultBeanFactory;

    @Before
    public void setUp(){
        accountV4Dao = new AccountV4Dao();
        itemV4Dao = new ItemV4Dao();
        defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinition(resource);
    }

    @Test
    public void testGetInjectionMetadata(){
        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
        processor.setBeanFactory(defaultBeanFactory);
        InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(PetStoreServiceV4.class);
        List<InjectionElement> injectionElements = injectionMetadata.getInjectionElements();

        assertEquals(2, injectionElements.size());

        assertFieldExists(injectionElements,"accountV4Dao");
        assertFieldExists(injectionElements,"itemV4Dao");

        PetStoreServiceV4 petStoreServiceV4 = new PetStoreServiceV4();

        injectionMetadata.inject(petStoreServiceV4);

        assertTrue(petStoreServiceV4.getAccountV4Dao() instanceof AccountV4Dao);
        assertTrue(petStoreServiceV4.getItemV4Dao() instanceof ItemV4Dao);

    }

    private void assertFieldExists(List<InjectionElement> elements,String fieldName){
        for (InjectionElement element : elements) {
            AutowiredFieldElement fieldElement = (AutowiredFieldElement) element;
            Field field = fieldElement.getField();
            if (field.getName().equals(fieldName)) {
                return;
            }
        }
        Assert.fail(fieldName+" does not exist!");
    }
}
