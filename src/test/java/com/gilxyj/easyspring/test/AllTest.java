package com.gilxyj.easyspring.test;


import com.gilxyj.easyspring.test.v1.V1AllTest;
import com.gilxyj.easyspring.test.v2.V2AllTest;
import com.gilxyj.easyspring.test.v3.V3AllTest;
import com.gilxyj.easyspring.test.v4.V4AllTest;
import com.gilxyj.easyspring.test.v5.V5AllTest;
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
@SuiteClasses({V1AllTest.class, V2AllTest.class, V3AllTest.class, V4AllTest.class, V5AllTest.class})
public class AllTest {

}
