package com.gilxyj.easyspring.test.v3;

import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.context.support.ClassPathXmlApplicationContext;

import com.gilxyj.easyspring.dao.v3.AccountDao;
import com.gilxyj.easyspring.service.v3.PetStoreServiceV3;
import org.junit.Test;

import static org.junit.Assert.*;

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
public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperties(){
        ApplicationContext ctx =new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreServiceV3 petStore = (PetStoreServiceV3)ctx.getBean("petStoreV3");
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
        assertEquals(1,petStore.getVersion());
    }

}
