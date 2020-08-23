package com.gilxyj.easyspring.beans.factory.support;

import com.gilxyj.easyspring.beans.factory.config.SingletonBeanRegistry;
import com.gilxyj.easyspring.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
 * @create: 2020-08-23 22:27
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    public final static Map<String, Object>singletonObjects =new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object object) {
        Assert.notNull(beanName,"'beanName' must not be null");
        Object oldObject = singletonObjects.get(beanName);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + object +"] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
        }
        if (singletonObjects.get(beanName) == null) {
            synchronized (DefaultSingletonBeanRegistry.class) {
                if (singletonObjects.get(beanName) == null) {
                    singletonObjects.put(beanName, object);
                }
            }
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
}
