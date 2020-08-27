package com.gilxyj.easyspring.beans;

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
 * @create: 2020-08-26 00:00
 **/
public class TypeMismatchException extends BeansException{

    private transient Object value;

    private Class<?> requiredType;


    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value :"+value + "to type "+requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }


}
