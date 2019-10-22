package com.huzihan.my_spring.aop;

public interface ClassFilter {

    /**
     * 用于匹配targetClass是否要拦截的类
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);

}
