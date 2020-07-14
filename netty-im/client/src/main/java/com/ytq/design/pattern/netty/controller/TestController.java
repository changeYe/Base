package com.ytq.design.pattern.netty.controller;

import com.ytq.design.pattern.netty.client.NettyClient;
import com.ytq.design.pattern.netty.codec.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-13
 */
@RestController
public class TestController {

    @Autowired
    NettyClient nettyClient;

    @GetMapping("/send")
    public String hello(String type,String message){

        Invocation invocation = new Invocation(type, message);
        nettyClient.send(invocation);
        return "没有返回结果的，不知道情况如何";
    }
}
