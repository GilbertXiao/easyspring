package com.gilxyj.easyspring.test.v2;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.PropertyValue;
import com.gilxyj.easyspring.beans.factory.config.RuntimeBeanReference;
import com.gilxyj.easyspring.beans.factory.config.TypedStringValue;
import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
 * @create: 2020-08-24 23:51
 **/
public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition(){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
        BeanDefinition beanDefinition = defaultBeanFactory.getBeanDefinition("petStoreV2");
        List<PropertyValue> pvs = beanDefinition.getPropertyValues();
        Assert.assertTrue(pvs.size() == 4);
        PropertyValue pv1 = this.getPropertyValue("accountDao", pvs);

        Assert.assertNotNull(pv1);

        Assert.assertTrue(pv1.getValue() instanceof RuntimeBeanReference);

        PropertyValue pv2 = this.getPropertyValue("itemDao", pvs);

        Assert.assertNotNull(pv2);

        Assert.assertTrue(pv2.getValue() instanceof RuntimeBeanReference);

        PropertyValue pv3 = this.getPropertyValue("age", pvs);

        Assert.assertNotNull(pv3);

        Assert.assertTrue(pv3.getValue() instanceof TypedStringValue);


    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs) {
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}
