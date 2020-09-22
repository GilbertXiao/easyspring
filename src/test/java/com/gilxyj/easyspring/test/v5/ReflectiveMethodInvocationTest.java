package com.gilxyj.easyspring.test.v5;

import com.gilxyj.easyspring.aop.aspectj.AspectJAfterAdvice;
import com.gilxyj.easyspring.aop.aspectj.AspectJAfterThrowingAdvice;
import com.gilxyj.easyspring.aop.aspectj.AspectJBeforeAdvice;
import com.gilxyj.easyspring.aop.framework.ReflectiveMethodInvocation;
import com.gilxyj.easyspring.service.v5.PetStoreServiceV5;
import com.gilxyj.easyspring.tx.TransactionManager;
import com.gilxyj.easyspring.util.MessageTracker;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
 * @create: 2020-09-20 00:21
 **/
public class ReflectiveMethodInvocationTest {

    private AspectJBeforeAdvice beforeAdvice = null;
    private AspectJAfterAdvice afterAdvice = null;
    private AspectJAfterThrowingAdvice afterThrowingAdvice = null;
    private PetStoreServiceV5 petStoreServiceV5 = null;
    private TransactionManager tx = null;

    @Before
    public void setUp() throws Exception {
        petStoreServiceV5 = new PetStoreServiceV5();
        tx = new TransactionManager();
        MessageTracker.clearMsgs();

        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"), null, tx);
        afterAdvice = new AspectJAfterAdvice(TransactionManager.class.getMethod("commit"), null, tx);
        afterThrowingAdvice = new AspectJAfterThrowingAdvice(TransactionManager.class.getMethod("rollback"), null, tx);
    }

    @Test
    public void testMethodInvocation() throws Throwable {
        Method targetMethod = PetStoreServiceV5.class.getMethod("placeOrder");

        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(beforeAdvice);
        interceptorList.add(afterAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreServiceV5, targetMethod, new Object[0], interceptorList);

        mi.proceed();

        List<String> msgs = MessageTracker.getMsgs();
        assertEquals(3, msgs.size());
        assertEquals("start tx", msgs.get(0));
        assertEquals("place Order", msgs.get(1));
        assertEquals("commit tx", msgs.get(2));


    }

    @Test
    public void testMethodInvocation2() throws Throwable {
        Method targetMethod = PetStoreServiceV5.class.getMethod("placeOrder");

        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(afterAdvice);
        interceptorList.add(beforeAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreServiceV5, targetMethod, new Object[0], interceptorList);

        mi.proceed();

        List<String> msgs = MessageTracker.getMsgs();
        assertEquals(3, msgs.size());
        assertEquals("start tx", msgs.get(0));
        assertEquals("place Order", msgs.get(1));
        assertEquals("commit tx", msgs.get(2));
    }

    @Test
    public void testMethodInvocation3() throws Throwable {
        Method targetMethod = PetStoreServiceV5.class.getMethod("placeOrderWithException");

        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(afterThrowingAdvice);
        interceptorList.add(beforeAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreServiceV5, targetMethod, new Object[0], interceptorList);

        try {
            mi.proceed();
        } catch (Throwable throwable) {
            List<String> msgs = MessageTracker.getMsgs();
            assertEquals(2, msgs.size());
            assertEquals("start tx", msgs.get(0));
            assertEquals("rollback tx", msgs.get(1));
            return;
        }

        fail("No Exception thrown");

    }
}
