package com.gilxyj.easyspring.beans.factory.config;

import java.io.File;
import java.lang.reflect.Field;

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
 * @create: 2020-09-10 01:52
 **/
public class DependencyDescriptor {

    private Field field;

    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        this.field = field;
        this.required = required;
    }

    public Class getDependencyType(){
        if (this.field != null) {
            return field.getType();
        }
        throw new RuntimeException("only support field dependency");
    }



    public boolean isRequired() {
        return required;
    }
}


