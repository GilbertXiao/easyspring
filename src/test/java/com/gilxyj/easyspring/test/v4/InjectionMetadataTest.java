package com.gilxyj.easyspring.test.v4;

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
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

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
 * @create: 2020-09-13 13:20
 **/
public class InjectionMetadataTest {

    @Test
    public void testInjection() throws Exception{
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinition(resource);

        Class<PetStoreServiceV4> clz = PetStoreServiceV4.class;
        LinkedList<InjectionElement> elements = new LinkedList<>();

        {
            Field accountV4Dao = PetStoreServiceV4.class.getDeclaredField("accountV4Dao");
            InjectionElement injectionElement = new AutowiredFieldElement(accountV4Dao, true, defaultBeanFactory);
            elements.add(injectionElement);

        }

        {
            Field itemV4Dao = PetStoreServiceV4.class.getDeclaredField("itemV4Dao");
            InjectionElement injectionElement = new AutowiredFieldElement(itemV4Dao, true, defaultBeanFactory);
            elements.add(injectionElement);
        }

        InjectionMetadata metadata = new InjectionMetadata(clz, elements);
        PetStoreServiceV4 petStoreServiceV4 = new PetStoreServiceV4();
        metadata.inject(petStoreServiceV4);
        assertTrue(petStoreServiceV4.getAccountV4Dao() instanceof AccountV4Dao);
        assertTrue(petStoreServiceV4.getItemV4Dao() instanceof ItemV4Dao);

    }

}
