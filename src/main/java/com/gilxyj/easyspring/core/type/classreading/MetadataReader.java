package com.gilxyj.easyspring.core.type.classreading;

import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.core.type.AnnotationMetadata;
import com.gilxyj.easyspring.core.type.ClassMetadata;

public interface MetadataReader {

    Resource getResource();

    AnnotationMetadata getAnnotationMetadata();

    ClassMetadata getClassMetadata();
}
