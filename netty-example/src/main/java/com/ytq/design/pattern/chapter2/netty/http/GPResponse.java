package com.ytq.design.pattern.chapter2.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.io.OutputStream;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class GPResponse {

    private final ChannelHandlerContext ctx;
    private final HttpRequest http;

    public GPResponse(ChannelHandlerContext ctx, HttpRequest httpRequest) {
       this.ctx = ctx;
       this.http = httpRequest;
    }

    public void write(String str){
        // 如果要http返回，我们需要定义http请求头规范
        boolean keepAlive = HttpUtil.isKeepAlive(http);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(str.getBytes()));


        response.headers()
                .set(CONTENT_TYPE, TEXT_PLAIN)
                .setInt(CONTENT_LENGTH, response.content().readableBytes());

        ChannelFuture f = ctx.write(response);

        if (!keepAlive) {
            f.addListener(ChannelFutureListener.CLOSE);
        }


    }
}
