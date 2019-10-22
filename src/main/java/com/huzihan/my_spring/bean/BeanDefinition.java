package com.huzihan.my_spring.bean;


public class BeanDefinition {

    private Object bean;

    /**
     * bean的名字
     */
    private String beanClassName;

    /**
     * bean的类型
     * 根据其 类型 可以生成一个类实例，然后可以把 属性 注入进去。
     */
    private Class beanClass;

    /**
     * bean的属性集合
     * 每个属性都是键值对 String - Object
     */
    private PropertyValues propertyValues = new PropertyValues();

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;

        try {
            this.beanClass = Class.forName(this.beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
