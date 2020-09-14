package com.gilxyj.easyspring.beans.factory.annotation;

import com.gilxyj.easyspring.beans.factory.config.AutowireCapableBeanFatory;

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
 * @create: 2020-09-13 17:12
 **/
public abstract class InjectionElement {

    protected Member member;

    protected AutowireCapableBeanFatory fatory;

    public InjectionElement(Member member, AutowireCapableBeanFatory fatory) {
        this.member = member;
        this.fatory = fatory;
    }

    public abstract void inject(Object object);
}
