package com.ytq.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yuantongqin
 * 2020-05-13
 */
public class JdkProxy implements InvocationHandler {

    private Object proxyObject;
    public JdkProxy(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("处理之前");
        Object invoke = method.invoke(proxyObject, args);
        System.out.println("处理之后");

        return invoke;
    }
}
