package com.gilxyj.easyspring.test.v1;

import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.context.support.ClassPathXmlApplicationContext;
import com.gilxyj.easyspring.context.support.FileSystemXMLApplicationContext;
import com.gilxyj.easyspring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

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
 * @create: 2020-08-23 18:15
 **/
public class ApplicationContextTestV1 {

    @Test
    public void testGetBean(){
        ApplicationContext ctx =new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext ctx =new FileSystemXMLApplicationContext("src\\test\\resources\\petstore-v1.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
