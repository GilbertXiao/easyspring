package com.gilxyj.easyspring.test.v2;

import com.gilxyj.easyspring.beans.SimpleTypeConverter;
import com.gilxyj.easyspring.beans.TypeConverter;
import com.gilxyj.easyspring.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
 * @create: 2020-08-26 00:19
 **/
public class TypeConverterTest {


    @Test
    public void testConventerStringToInt(){
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        assertEquals(3, i.intValue());

        try {
            converter.convertIfNecessary("j", Integer.class);
        } catch (TypeMismatchException e) {
            e.printStackTrace();
            return;
        }
        fail();


    }

    @Test
    public void testConventerStringToBoolean(){
        TypeConverter converter = new SimpleTypeConverter();
        Boolean i = converter.convertIfNecessary("true", Boolean.class);
        assertEquals(true, i.booleanValue());

        try {
            converter.convertIfNecessary("jfdasf", Boolean.class);
        } catch (TypeMismatchException e) {
            e.printStackTrace();
            return;
        }
        fail();


    }
}
