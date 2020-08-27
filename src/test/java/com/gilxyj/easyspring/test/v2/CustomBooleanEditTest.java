package com.gilxyj.easyspring.test.v2;

import com.gilxyj.easyspring.beans.propertyeditor.CustomBooleanEditor;
import com.gilxyj.easyspring.beans.propertyeditor.CustomNumberEditor;
import org.junit.Test;

import javax.swing.text.EditorKit;
import java.util.TreeMap;

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
public class CustomBooleanEditTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConvertString(){
        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("true");
        assertEquals(true, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("false");
        assertEquals(false, ((Boolean) editor.getValue()).booleanValue());

        editor.setAsText("on");
        assertEquals(true, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("off");
        assertEquals(false, ((Boolean) editor.getValue()).booleanValue());

        editor.setAsText("yes");
        assertEquals(true, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("no");
        assertEquals(false, ((Boolean) editor.getValue()).booleanValue());

        editor.setAsText("aabbcc");

    }


}
