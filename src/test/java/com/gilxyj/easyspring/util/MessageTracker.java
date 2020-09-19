package com.gilxyj.easyspring.util;

import java.util.ArrayList;
import java.util.List;

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
 * @create: 2020-09-17 21:45
 **/
public class MessageTracker {

    private static List<String> MESSAGES = new ArrayList<>();

    public static void addMsg(String msg) {
        MESSAGES.add(msg);
    }

    public static void clearMsgs(){
        MESSAGES.clear();
    }

    public static  List<String> getMsgs(){
        return MESSAGES;
    }
}
