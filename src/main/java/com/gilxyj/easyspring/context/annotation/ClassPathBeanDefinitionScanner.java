package com.gilxyj.easyspring.context.annotation;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.factory.BeanDefinitionStoreException;
import com.gilxyj.easyspring.beans.factory.support.BeanNameGenerator;
import com.gilxyj.easyspring.beans.support.BeanDefinitionRegistry;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.core.io.support.PackageResourceLoader;
import com.gilxyj.easyspring.core.type.classreading.MetadataReader;
import com.gilxyj.easyspring.core.type.classreading.SimpleMetadataReader;
import com.gilxyj.easyspring.stereotype.Component;
import com.gilxyj.easyspring.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
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
 * @create: 2020-09-06 21:13
 **/
public class ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;

    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    private static final Logger LOGGER= LoggerFactory.getLogger(ClassPathBeanDefinitionScanner.class);

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public Set<BeanDefinition> doScan(String packagesToscan){
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToscan, ",");

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getId(), candidate);
            }
        }
        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        LinkedHashSet<BeanDefinition> candidates = new LinkedHashSet<>();

        Resource[] resources = this.resourceLoader.getResources(basePackage);
        for (Resource resource : resources) {
            try {
                 MetadataReader metadataReader = new SimpleMetadataReader(resource);
                if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                    ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                    String beanName = this.beanNameGenerator.generateBeanName(sbd, this.registry);
                    sbd.setId(beanName);
                    candidates.add(sbd);
                }
            } catch (IOException e) {
                throw new BeanDefinitionStoreException(
                        "Failed to read candidate component class: " + resource, e);
            }

        }

        return candidates;
    }
}
