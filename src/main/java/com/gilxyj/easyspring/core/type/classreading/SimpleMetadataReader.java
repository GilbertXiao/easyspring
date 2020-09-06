package com.gilxyj.easyspring.core.type.classreading;

import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.core.type.AnnotationMetadata;
import com.gilxyj.easyspring.core.type.ClassMetadata;
import org.springframework.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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
 * @create: 2020-09-06 18:04
 **/
public class SimpleMetadataReader implements MetadataReader {

    private final Resource resource;

    private final ClassMetadata classMetadata;

    private final AnnotationMetadata annottionMetadata;



    public SimpleMetadataReader(Resource resource) throws IOException {

        ClassReader classReader;

        try(InputStream is = new BufferedInputStream(resource.getInputStream());){
            classReader = new ClassReader(is);
        }

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        this.annottionMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annottionMetadata;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }
}
