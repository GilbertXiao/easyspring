package com.gilxyj.easyspring.tx;

import com.gilxyj.easyspring.util.MessageTracker;

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
 * @create: 2020-09-17 21:44
 **/
public class TransactionManager {

    public void start(){
        System.out.println("start tx");
        MessageTracker.addMsg("start tx");
    }

    public void commit(){
        System.out.println("commit tx");
        MessageTracker.addMsg("commit tx");
    }

    public void rollback(){
        System.out.println("rollback tx");
        MessageTracker.addMsg("rollback tx");
    }

}
