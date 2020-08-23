package com.gilxyj.easyspring.context.support;

import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.core.io.ClassPathResource;
import com.gilxyj.easyspring.core.io.Resource;

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
 * @create: 2020-08-23 18:27
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{


    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    Resource getResource(String configFile) {
        return  new ClassPathResource(configFile,this.getBeanClassLoader());
    }
}
