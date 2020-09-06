package com.gilxyj.easyspring.service.v4;

import com.gilxyj.easyspring.beans.factory.annotation.Autowired;

import com.gilxyj.easyspring.dao.v4.AccountDao;
import com.gilxyj.easyspring.dao.v4.ItemDao;
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
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;


    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
