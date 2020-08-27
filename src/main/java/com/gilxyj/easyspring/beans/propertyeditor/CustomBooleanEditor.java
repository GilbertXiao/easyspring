package com.gilxyj.easyspring.beans.propertyeditor;

import com.gilxyj.easyspring.util.StringUtils;

import java.beans.PropertyEditorSupport;

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
 * @create: 2020-08-25 23:20
 **/
public class CustomBooleanEditor extends PropertyEditorSupport {

    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";

    public static final String VALUE_ON = "on";
    public static final String VALUE_OFF = "off";

    public static final String VALUE_YES = "yes";
    public static final String VALUE_NO = "no";

    public static final String VALUE_1 = "1";
    public static final String VALUE_0 = "0";

    private final boolean allowEmpty;


    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String input = (text != null ? StringUtils.trimAllWhitespace(text) : null);
        if (this.allowEmpty && !StringUtils.hasLength(input)) {
            setValue(null);
            return;
        }
        if ((VALUE_TRUE.equalsIgnoreCase(input) || VALUE_ON.equalsIgnoreCase(input) ||
                VALUE_YES.equalsIgnoreCase(input) || VALUE_1.equals(input))) {
            setValue(Boolean.TRUE);
        } else if ((VALUE_FALSE.equalsIgnoreCase(input) || VALUE_OFF.equalsIgnoreCase(input) ||
                VALUE_NO.equalsIgnoreCase(input) || VALUE_0.equals(input))) {
            setValue(Boolean.FALSE);
        } else {
            throw new IllegalArgumentException("Invalid boolean value [" + text + "]");
        }
    }

    @Override
    public String getAsText() {
        if (Boolean.TRUE.equals(getValue())) {
            return VALUE_TRUE;
        }
        else if (Boolean.FALSE.equals(getValue())) {
            return VALUE_FALSE;
        }
        else {
            return "";
        }
    }
}
