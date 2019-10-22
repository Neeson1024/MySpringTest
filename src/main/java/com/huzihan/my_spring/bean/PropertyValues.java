package com.huzihan.my_spring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装一个对象所有的PropertyValue。
 * 为什么封装而不是直接用List? 因为可以封装一些操作。
 *
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
