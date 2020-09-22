package com.gilxyj.easyspring.aop.framework;

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
 * @create: 2020-09-21 01:15
 **/
public class AopConfigException extends RuntimeException {

    public AopConfigException(String s) {
        super(s);
    }

    public AopConfigException(String s, Exception e) {
        super(s, e);
    }
}
