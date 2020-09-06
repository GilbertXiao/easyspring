package com.gilxyj.easyspring.context.annotation;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.annotation.AnnotatedBeanDefinition;
import com.gilxyj.easyspring.beans.factory.support.BeanNameGenerator;
import com.gilxyj.easyspring.beans.support.BeanDefinitionRegistry;
import com.gilxyj.easyspring.core.annotation.AnnotationAttributes;
import com.gilxyj.easyspring.core.type.AnnotationMetadata;
import com.gilxyj.easyspring.util.ClassUtils;
import com.gilxyj.easyspring.util.StringUtils;

import java.beans.Introspector;
import java.util.Set;

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
 * @create: 2020-09-06 21:34
 **/
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if(definition instanceof AnnotatedBeanDefinition){
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition)definition);
            if (StringUtils.hasText(beanName)) {
                return beanName;
            }

        }
        return buildDefaultBeanName(definition, registry);
    }

    protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return buildDefaultBeanName(definition);
    }

    private String buildDefaultBeanName(BeanDefinition definition) {
        String shortClassName =  ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName);
    }

    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition definition) {
        AnnotationMetadata metadata = definition.getMetadata();
        Set<String> types = metadata.getAnnotationTypes();
        String beanName = null;
        for (String type : types) {
            AnnotationAttributes attributes = metadata.getAnnotationAttributes(type);
            if (attributes.get("value") != null) {
                Object value = attributes.get("value");
                if (value instanceof String) {
                    String strVal = (String) value;
                    if (StringUtils.hasLength(strVal)) {
                        beanName = strVal;
                    }
                }
            }
        }
        return beanName;
    }
}
