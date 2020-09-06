package com.gilxyj.easyspring.core.annotation;

import com.gilxyj.easyspring.util.Assert;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

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
 * @create: 2020-09-03 23:48
 **/
public class AnnotationAttributes extends LinkedHashMap<String,Object> {

    public AnnotationAttributes(){

    }

    public AnnotationAttributes(int initialCapacity){
        super(initialCapacity);
    }

    public AnnotationAttributes(Map<String,Object> map){
        super(map);
    }

    private <T> T doGet(String attributeName,Class<T> expectedType){
        Object value = this.get(attributeName);
        Assert.notNull(value,String.format("Attribute '%s' not found ", attributeName));
        return (T) value;
    }

    public String getString(String attributeName){
        return doGet(attributeName, String.class);
    }

    public String[] getStringArray(String attribute){
        return doGet(attribute, String[].class);
    }

    public boolean getBoolean(String attributeName){
        return doGet(attributeName, Boolean.class);
    }

    public <N extends Number> N getNumber(String attributeName){
        return (N) doGet(attributeName, Integer.class);
    }

    public <E extends Enum<?>> E getEnum(String attributeName){
        return (E) doGet(attributeName, Enum.class);
    }

    public Class getClass(String attributeName){
        return doGet(attributeName, Class.class);
    }

    public Class[] getClassArray(String attributeName){
        return doGet(attributeName, Class[].class);
    }



}
