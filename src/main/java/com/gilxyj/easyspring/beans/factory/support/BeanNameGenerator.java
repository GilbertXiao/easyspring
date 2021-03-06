package com.gilxyj.easyspring.beans.factory.support;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.support.BeanDefinitionRegistry;

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
 * @create: 2020-09-06 21:33
 **/
public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
