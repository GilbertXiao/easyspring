package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.core.annotation.AnnotationAttributes;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.type.AnnotationMetadata;
import com.gilxyj.easyspring.core.type.classreading.MetadataReader;
import com.gilxyj.easyspring.core.type.classreading.SimpleMetadataReader;
import com.gilxyj.easyspring.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
 * @create: 2020-09-06 15:17
 **/
public class MetadataReaderTest {


    @Test
    public void testGetMetadata() throws IOException{
        ClassPathResource resource = new ClassPathResource("com/gilxyj/easyspring/service/v4/PetStoreServiceV4.class");

        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();


        assertTrue(amd.hasAnnotation(annotation));

        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        assertEquals("petStoreServiceV4",attributes.get("value"));

        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("com.gilxyj.easyspring.service.v4.PetStoreServiceV4", amd.getClassName());



    }
}
