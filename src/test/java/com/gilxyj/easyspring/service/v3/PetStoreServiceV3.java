package com.gilxyj.easyspring.service.v3;

import com.gilxyj.easyspring.dao.v3.AccountDao;
import com.gilxyj.easyspring.dao.v3.ItemDao;


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
public class PetStoreServiceV3 {

    private AccountDao accountDao;
    private ItemDao itemDao;
    private int version;


    public PetStoreServiceV3(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = 1;
    }
    public PetStoreServiceV3(AccountDao accountDao, ItemDao itemDao, int version) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = version;
    }


    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public int getVersion() {
        return version;
    }
}
