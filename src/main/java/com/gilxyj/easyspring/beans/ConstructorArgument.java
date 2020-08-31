package com.gilxyj.easyspring.beans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
 * @create: 2020-08-29 13:45
 **/
public class ConstructorArgument {

    private final List<ValueHoder> argumentValues = new LinkedList<>();

    public List<ValueHoder> getArgumentValues() {
        return Collections.unmodifiableList(argumentValues);
    }

    public ConstructorArgument() {
    }

    public void addArgumentValue(ValueHoder valueHoder) {
        this.argumentValues.add(valueHoder);
    }

    public int getArgumentCount(){
        return this.argumentValues.size();
    }

    public boolean isEmpty(){
        return this.argumentValues.isEmpty();
    }

    public void clear(){
        this.argumentValues.clear();
    }

    public static class ValueHoder{

        private Object value;
        private String type;
        private String name;

        public ValueHoder(Object value) {
            this.value = value;
        }

        public ValueHoder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public ValueHoder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
