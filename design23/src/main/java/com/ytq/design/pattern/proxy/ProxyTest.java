package com.ytq.design.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * @author yuantongqin
 * 2020-05-13
 */
public class ProxyTest {

    public static void main(String[] args) {

        Mapper mapper = new MyMapper();

        Mapper o = (Mapper) Proxy.newProxyInstance(mapper.getClass().getClassLoader(), new Class<?>[]{Mapper.class}, new JdkProxy(mapper));
        String name = o.getMapper("张三");
        System.out.println(name);

        
    }
}
