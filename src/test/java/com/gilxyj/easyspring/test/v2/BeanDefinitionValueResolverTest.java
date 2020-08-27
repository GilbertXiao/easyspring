package com.gilxyj.easyspring.test.v2;

import com.gilxyj.easyspring.beans.factory.config.RuntimeBeanReference;
import com.gilxyj.easyspring.beans.factory.config.TypedStringValue;
import com.gilxyj.easyspring.beans.factory.support.BeanDefinitionValueResolver;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.dao.v2.ItemDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
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
 * @create: 2020-08-25 01:22
 **/
public class BeanDefinitionValueResolverTest {

    private BeanDefinitionValueResolver resolver;

    @Before
    public void setUp() {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));

        resolver= new BeanDefinitionValueResolver(defaultBeanFactory);
    }

    @Test
    public void testResolveRuntimeBeanReference(){

        RuntimeBeanReference reference = new RuntimeBeanReference("itemDao");

        Object value = resolver.resolveValueIfNeccessary(reference);

        assertNotNull(value);
        assertTrue(value instanceof ItemDao);

    }

    @Test
    public void testResolveTypedStringValue(){

        TypedStringValue reference = new TypedStringValue("age");

        Object value = resolver.resolveValueIfNeccessary(reference);

        assertNotNull(value);
        assertTrue(value instanceof String);

    }



}
