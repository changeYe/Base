package com.ytq.design.pattern.chapter3.netty.provider;

import com.ytq.design.pattern.chapter3.netty.api.RpcHelloService;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
public class RpcHelloServiceImpl implements RpcHelloService {
    @Override
    public String hello(String name) {
        System.out.println("参数："+name);
        return "hello 你好："+name;
    }
}
