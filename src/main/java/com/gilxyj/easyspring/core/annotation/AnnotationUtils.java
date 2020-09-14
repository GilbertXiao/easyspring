package com.gilxyj.easyspring.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

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
 * @create: 2020-09-13 23:39
 **/
public  abstract class AnnotationUtils {


    public static <T extends Annotation> T getAnnotation(AccessibleObject accessibleObject, Class<T> type) {
        T annotation = accessibleObject.getAnnotation(type);
        if (annotation == null) {
            for (Annotation metaAnn : accessibleObject.getAnnotations()) {
                annotation = metaAnn.annotationType().getAnnotation(type);
                if (annotation != null) {
                    break;
                }
            }
        }
        return annotation;
    }
}
