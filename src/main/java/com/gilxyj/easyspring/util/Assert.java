package com.gilxyj.easyspring.util;

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
 * @create: 2020-08-23 19:26
 **/
public abstract class Assert {
    public static void notNull(Object object, String msg) {
        if (object == null) {
            throw new IllegalArgumentException(msg);
        }
    }

}
