package com.gilxyj.easyspring.test.v5;

import com.gilxyj.easyspring.aop.aspectj.AspectJAfterAdvice;
import com.gilxyj.easyspring.aop.aspectj.AspectJAfterThrowingAdvice;
import com.gilxyj.easyspring.aop.aspectj.AspectJBeforeAdvice;
import com.gilxyj.easyspring.aop.aspectj.AspectJExpressionPointcut;
import com.gilxyj.easyspring.aop.framework.AopConfig;
import com.gilxyj.easyspring.aop.framework.AopConfigSupport;
import com.gilxyj.easyspring.aop.framework.CglibProxyFactory;
import com.gilxyj.easyspring.service.v5.PetStoreServiceV5;
import com.gilxyj.easyspring.tx.TransactionManager;
import com.gilxyj.easyspring.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
 * @create: 2020-09-21 00:37
 **/
public class CglibAopProxyTest {


    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterAdvice afterAdvice = null;
    private static AspectJAfterThrowingAdvice aspectJAfterThrowingAdvice = null;
    private static AspectJExpressionPointcut pc = null;

    private TransactionManager transactionManager;

    @Before
    public void setUp() throws Exception {
        transactionManager = new TransactionManager();
        String expression = "execution (* com.gilxyj.easyspring.service.v5.*.placeOrder(..))";

        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"), pc, transactionManager);
        afterAdvice = new AspectJAfterAdvice(TransactionManager.class.getMethod("commit"), pc, transactionManager);
        aspectJAfterThrowingAdvice = new AspectJAfterThrowingAdvice(TransactionManager.class.getMethod("rollback"), pc, transactionManager);
    }

    @Test
    public void testGetProxy(){
        AopConfig config = new AopConfigSupport();
        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(new PetStoreServiceV5());

        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);
        PetStoreServiceV5 petStoreServiceV5 = (PetStoreServiceV5) proxyFactory.getProxy();
        petStoreServiceV5.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        assertEquals(3, msgs.size());
        assertEquals("start tx", msgs.get(0));
        assertEquals("place Order", msgs.get(1));
        assertEquals("commit tx", msgs.get(2));

        petStoreServiceV5.toString();

    }
}
