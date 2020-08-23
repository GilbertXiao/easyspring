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
 * @create: 2020-08-23 01:10
 **/
public abstract class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;

        cl = Thread.currentThread().getContextClassLoader();

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                cl = ClassLoader.getSystemClassLoader();
            }
        }

        return cl;
    }


}
