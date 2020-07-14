package com.ytq.design.pattern.prototype;

/**
 * @author yuantongqin
 * 2020-05-12
 * 原型模式实现方式：深克隆、浅克隆两种方式
 */
public class Student implements Cloneable {


    public String name;
    private Integer age;

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Object clone = super.clone();
       if(clone == null){
           throw new NullPointerException("student clone is null");
       }
        return (Student) clone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
