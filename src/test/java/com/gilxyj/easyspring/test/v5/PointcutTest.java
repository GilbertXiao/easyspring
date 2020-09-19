package com.gilxyj.easyspring.test.v5;

import com.gilxyj.easyspring.aop.MethodMatcher;
import com.gilxyj.easyspring.aop.aspectj.AspectJExpressionPointcut;
import com.gilxyj.easyspring.service.v4.PetStoreServiceV4;
import com.gilxyj.easyspring.service.v5.PetStoreServiceV5;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

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
 * @create: 2020-09-18 02:12
 **/
public class PointcutTest {

    @Test
    public void testPointcut() throws Exception{

        String expression = "execution (* com.gilxyj.easyspring.service.v5.*.placeOrder(..))";

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        MethodMatcher mm = pc.getMethodMatcher();

        {
            Class<PetStoreServiceV5> targetClass = PetStoreServiceV5.class;
            Method method1 = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(method1));
            Method method2 = targetClass.getMethod("getAccountV5Dao");
            Assert.assertFalse(mm.matches(method2));
        }

        {
            Class<PetStoreServiceV4> targetClass = PetStoreServiceV4.class;
            Method method1 = targetClass.getMethod("getAccountV4Dao");
            Assert.assertFalse(mm.matches(method1));
        }

    }
}
