package com.ytq.design.pattern.proxy;

/**
 * @author yuantongqin
 * 2020-05-13
 */
public class MyMapper implements Mapper {

    @Override
    public String getMapper(String name) {

        return "你好呀 "+name;
    }
}
