package com.gilxyj.easyspring.test.v2;

import com.gilxyj.easyspring.beans.propertyeditor.CustomNumberEditor;

import com.gilxyj.easyspring.util.NumberUtils;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
 * @create: 2020-08-25 09:39
 **/
public class CustomNumberEditTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConvertString(){
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("3");

        Object value = editor.getValue();
        assertTrue(value instanceof Integer);
        assertTrue(value instanceof Number);
        assertEquals(3, ((Integer)editor.getValue()).intValue());

        editor.setAsText("");
        assertTrue(editor.getValue() == null);
        editor.setAsText("3.1");


    }


}
