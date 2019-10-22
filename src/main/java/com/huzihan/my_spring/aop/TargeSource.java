package com.huzihan.my_spring.aop;

public class TargeSource {

    //原对象
    private Object targe;

    private Class<?> targetClass;

    private Class<?>[] interfaces;

    public TargeSource(Object targe, Class<?> targetClass, Class<?>... interfaces) {
        this.targe = targe;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Object getTarge() {
        return targe;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }
}
