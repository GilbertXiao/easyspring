package com.gilxyj.easyspring.service.v4;

import com.gilxyj.easyspring.beans.factory.annotation.Autowired;

import com.gilxyj.easyspring.dao.v4.AccountV4Dao;
import com.gilxyj.easyspring.dao.v4.ItemV4Dao;
import com.gilxyj.easyspring.stereotype.Component;



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
@Component(value = "petStoreServiceV4")
public class PetStoreServiceV4 {


    @Autowired
    private AccountV4Dao accountV4Dao;

    @Autowired
    private ItemV4Dao itemV4Dao;


    public AccountV4Dao getAccountV4Dao() {
        return accountV4Dao;
    }

    public ItemV4Dao getItemV4Dao() {
        return itemV4Dao;
    }
}
