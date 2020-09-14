package com.gilxyj.easyspring.test.v4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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
 * @create: 2020-09-13 12:39
 **/
@RunWith(Suite.class)
@SuiteClasses({ApplicationContextTest4.class,AutowiredAnnotationProcessorTest.class,ClassPathBeanDefinitionScannerTest.class,ClassReaderTest.class,DependencyDescriptorTest.class,InjectionMetadataTest.class,MetadataReaderTest.class,PackageResourceLoaderTest.class,XmlBeanDefinitionReaderTest.class})
public class V4AllTest {

}
