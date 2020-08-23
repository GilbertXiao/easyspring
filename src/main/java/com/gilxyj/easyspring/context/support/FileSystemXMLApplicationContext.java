package com.gilxyj.easyspring.context.support;

import com.gilxyj.easyspring.beans.factory.support.DefaultBeanFactory;
import com.gilxyj.easyspring.beans.factory.xml.XMLBeanDefinitionReader;
import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.core.io.FileSystemResource;
import com.gilxyj.easyspring.core.io.Resource;

import java.net.ConnectException;

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
 * @create: 2020-08-23 20:34
 **/
public class FileSystemXMLApplicationContext  extends AbstractApplicationContext {

    public FileSystemXMLApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    Resource getResource(String configFile) {
        return new FileSystemResource(configFile);
    }

}
