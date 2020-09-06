package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.context.support.ClassPathXmlApplicationContext;
import com.gilxyj.easyspring.service.v4.PetStoreServiceV4;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
 * @create: 2020-08-31 21:21
 **/
public class ApplicationContextTest4 {

    @Test
    public void testGetBeanProperty(){
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStoreServiceV4 petStore = (PetStoreServiceV4) context.getBean("petStore");
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
    }
}
