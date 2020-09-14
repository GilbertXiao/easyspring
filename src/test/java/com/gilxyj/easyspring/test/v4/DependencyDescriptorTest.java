package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.beans.factory.config.DependencyDescriptor;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.dao.v4.AccountV4Dao;
import com.gilxyj.easyspring.service.v4.PetStoreServiceV4;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

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
 * @create: 2020-09-10 01:30
 **/

public class DependencyDescriptorTest {

    @Test
    public void testResolveDependency() throws Exception{
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        ClassPathResource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinition(resource);

        Field accountDao = PetStoreServiceV4.class.getDeclaredField("accountV4Dao");
        DependencyDescriptor descriptor = new DependencyDescriptor(accountDao, true);

        Object o = defaultBeanFactory.resolveDependency(descriptor);
        Assert.assertTrue( o  instanceof AccountV4Dao);
    }
}
