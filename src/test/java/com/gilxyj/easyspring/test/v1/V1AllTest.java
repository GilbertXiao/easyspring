package com.gilxyj.easyspring.test.v1;

import com.gilxyj.easyspring.beans.factory.BeanFactory;
import com.gilxyj.easyspring.context.ApplicationContext;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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
 * @create: 2020-08-23 18:45
 **/

@RunWith(Suite.class)
@SuiteClasses({ApplicationContextTest.class, BeanFactoryTest.class,ResourceTest.class})
public class V1AllTest {

}
