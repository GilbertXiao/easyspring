package com.gilxyj.easyspring.test.v2;

import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.context.support.ClassPathXmlApplicationContext;
import com.gilxyj.easyspring.dao.v2.AccountDao;

import com.gilxyj.easyspring.service.v2.PetStoreServiceV2;
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
 * @create: 2020-08-23 18:15
 **/
public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperties(){
        ApplicationContext ctx =new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreServiceV2 petStore = (PetStoreServiceV2)ctx.getBean("petStoreV2");
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
        assertNotNull(petStore.getAge());

        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getAge() instanceof String);
    }

}
