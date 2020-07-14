package com.ytq.design.pattern.chapter2.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import java.io.InputStream;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class GPRequest {

    public String method;
    public String url;
    public GPRequest(ChannelHandlerContext ctx, HttpRequest httpRequest) {
       url = httpRequest.uri();
       method = httpRequest.method().name();

    }

    public String getUrl() {

        return url;
    }


    public String getMethod() {

        return method;
    }
}
