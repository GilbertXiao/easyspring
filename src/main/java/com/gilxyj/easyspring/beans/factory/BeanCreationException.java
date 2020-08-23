package com.gilxyj.easyspring.beans.factory;

import com.gilxyj.easyspring.beans.BeansException;

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
 * @create: 2020-08-23 13:38
 **/
public class BeanCreationException extends BeansException {

    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException( String beanName,String message) {
        super("Error creating bean with name "+beanName+":"+message);
        this.beanName = beanName;
    }

    public BeanCreationException( String beanName,String message,Throwable cause) {
        this(beanName, message);
        initCause(cause);
    }

    public String getBeanName() {
        return beanName;
    }

}
