package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.core.annotation.AnnotationAttributes;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.io.Resource;

import com.gilxyj.easyspring.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.gilxyj.easyspring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @program: easyspring
 * @description:
 * @作者 飞码录
 * @微信公众号 飞码录
 * @网站 http://www/codesboy/cn
 * @国际站 http://www/codesboy/com
 * @微信 gilbertxy
 * @GitHub https://github/com/GilbertXiao
 * @Gitee https://gitee/com/gilbertxiao
 * @create: 2020-09-01 23:46
 **/
public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        Resource resource = new ClassPathResource("com/gilxyj/easyspring/service/v4/PetStoreServiceV4.class");
        ClassReader classReader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        assertFalse(visitor.isAbstract());
        assertFalse(visitor.isInterface());
        assertFalse(visitor.isAbstract());
        assertEquals("com.gilxyj.easyspring.service.v4.PetStoreServiceV4",visitor.getClassName());
        assertEquals("java.lang.Object",visitor.getSuperClassName());
        assertEquals(0,visitor.getInterfaceNames().length);

    }

    @Test
    public void testGetAnnotation() throws Exception{
        ClassPathResource resource = new ClassPathResource("com/gilxyj/easyspring/service/v4/PetStoreServiceV4.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "com.gilxyj.easyspring.stereotype.Component";

        assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        assertEquals("petStoreServiceV4",attributes.get("value"));


    }
}
