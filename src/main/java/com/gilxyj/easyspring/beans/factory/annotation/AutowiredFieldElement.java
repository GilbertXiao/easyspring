package com.gilxyj.easyspring.beans.factory.annotation;

import com.gilxyj.easyspring.beans.factory.BeanCreationException;
import com.gilxyj.easyspring.beans.factory.config.AutowireCapableBeanFatory;
import com.gilxyj.easyspring.beans.factory.config.DependencyDescriptor;
import com.gilxyj.easyspring.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

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
 * @create: 2020-09-13 18:53
 **/
public class AutowiredFieldElement extends InjectionElement{

    boolean required;

    public AutowiredFieldElement(Field f,boolean required, AutowireCapableBeanFatory fatory) {
        super(f, fatory);
        this.required = required;
    }

    public Field getField(){
        return (Field) this.member;
    }

    @Override
    public void inject(Object target) {
        Field field = this.getField();

        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, this.required);
        Object value = fatory.resolveDependency(dependencyDescriptor);

        if (value != null) {
            ReflectionUtils.makeAccessible(field);
            try {
                field.set(target,value);
            } catch (IllegalAccessException e) {
                throw new BeanCreationException("Could not autowire field: " + field, e);
            }
        }
    }
}
