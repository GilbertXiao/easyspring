package com.gilxyj.easyspring.test.v3;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.ConstructorArgument;
import com.gilxyj.easyspring.beans.ConstructorArgument.ValueHoder;
import com.gilxyj.easyspring.beans.factory.config.RuntimeBeanReference;
import com.gilxyj.easyspring.beans.factory.config.TypedStringValue;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
 * @create: 2020-08-29 13:27
 **/
public class BeanDefinitionTestV3 {

    @Test
    public void testConstructorArgument() {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition bd = beanFactory.getBeanDefinition("petStoreV3");
        assertEquals("com.gilxyj.easyspring.service.v3.PetStoreServiceV3", bd.getBeanClassName());

        ConstructorArgument constructorArgument = bd.getConstructorArgument();
        List<ValueHoder> argumentValues = constructorArgument.getArgumentValues();


        assertEquals(3, argumentValues.size());
        RuntimeBeanReference ref1 = (RuntimeBeanReference)argumentValues.get(0).getValue();
        assertEquals("accountDaoV3", ref1.getBeanName());

        RuntimeBeanReference ref2 = (RuntimeBeanReference)argumentValues.get(1).getValue();
        assertEquals("itemDaoV3", ref2.getBeanName());

        TypedStringValue strValue = (TypedStringValue)argumentValues.get(2).getValue();
        assertEquals("1", strValue.getValue());

    }
}
