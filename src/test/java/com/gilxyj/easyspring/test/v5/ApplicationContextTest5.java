package com.gilxyj.easyspring.test.v5;

import com.gilxyj.easyspring.context.ApplicationContext;
import com.gilxyj.easyspring.context.support.ClassPathXmlApplicationContext;
import com.gilxyj.easyspring.service.v5.PetStoreServiceV5;
import com.gilxyj.easyspring.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
 * @create: 2020-09-17 21:31
 **/
public class ApplicationContextTest5 {

    @Before
    public void setUp() throws Exception {
        MessageTracker.clearMsgs();
    }

    @Test
    public void testPlaceOrder() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v5.xml");
        PetStoreServiceV5 petStoreV5 = ((PetStoreServiceV5) ctx.getBean("petStoreServiceV5"));

        assertNotNull(petStoreV5.getAccountV5Dao());
        assertNotNull(petStoreV5.getItemV5Dao());

        petStoreV5.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        assertEquals(3, msgs.size());
        assertEquals("start tx", msgs.get(0));
        assertEquals("place order", msgs.get(1));
        assertEquals("commit tx", msgs.get(2));

    }
}
