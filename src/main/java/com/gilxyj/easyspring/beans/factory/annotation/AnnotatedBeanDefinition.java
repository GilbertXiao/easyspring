package com.gilxyj.easyspring.beans.factory.annotation;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.core.type.AnnotationMetadata;

public interface AnnotatedBeanDefinition  extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
