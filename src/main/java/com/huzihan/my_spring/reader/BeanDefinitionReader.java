package com.huzihan.my_spring.reader;

/**
 * 解析 BeanDefinition 的接口
 * 暴露加载bean定义的方法
 *
 */
public interface BeanDefinitionReader {

    /**
     * 根据地址加载bean的定义
     * @param location
     * @throws Exception
     */
    void loadBeanDefinition(String location) throws Exception;
}
