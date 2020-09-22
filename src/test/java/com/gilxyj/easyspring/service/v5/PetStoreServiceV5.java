package com.gilxyj.easyspring.service.v5;

import com.gilxyj.easyspring.beans.factory.annotation.Autowired;
import com.gilxyj.easyspring.dao.v5.AccountV5Dao;
import com.gilxyj.easyspring.dao.v5.ItemV5Dao;
import com.gilxyj.easyspring.stereotype.Component;
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
 * @create: 2020-08-23 00:01
 **/
@Component(value = "petStoreServiceV5")
public class PetStoreServiceV5 {

    @Autowired
    private AccountV5Dao accountV5Dao;

    @Autowired
    private ItemV5Dao itemV5Dao;

    public PetStoreServiceV5() {
    }

    public AccountV5Dao getAccountV5Dao() {
        return accountV5Dao;
    }

    public ItemV5Dao getItemV5Dao() {
        return itemV5Dao;
    }

    public void placeOrder(){
        System.out.println("place Order");
        MessageTracker.addMsg("place Order");
    }

    public void placeOrderWithException(){
        throw new NullPointerException();
    }
}
