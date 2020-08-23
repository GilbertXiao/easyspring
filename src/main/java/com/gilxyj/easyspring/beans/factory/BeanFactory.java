package com.gilxyj.easyspring.beans.factory;

import com.gilxyj.easyspring.beans.BeanDefinition;

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
 * @create: 2020-08-22 23:42
 **/
public interface BeanFactory {

    BeanDefinition getBeanDefinition(String petStore) ;

    Object getBean(String petStore);
}
