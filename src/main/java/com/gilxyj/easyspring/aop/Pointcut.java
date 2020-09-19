package com.gilxyj.easyspring.aop;

public interface Pointcut {

    MethodMatcher getMethodMatcher();
    String getExpression();
}
