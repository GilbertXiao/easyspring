package com.gilxyj.easyspring.beans.factory.annotation;

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
 * @create: 2020-09-13 21:20
 **/
public class InjectionMetadata {

    private final Class<?> targetClass;

    private List<InjectionElement> injectionElements;

    public InjectionMetadata(Class<?> targetClass, LinkedList<InjectionElement> injectionElements) {
        this.targetClass = targetClass;
        this.injectionElements = injectionElements;
    }

    public List<InjectionElement> getInjectionElements() {
        return injectionElements;
    }

    public void inject(Object target) {
        if (injectionElements == null || injectionElements.isEmpty()) {
            return;
        }
        for (InjectionElement injectionElement : injectionElements) {
            injectionElement.inject(target);

        }
    }
}
