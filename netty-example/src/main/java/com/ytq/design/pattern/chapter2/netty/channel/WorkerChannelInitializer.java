package com.ytq.design.pattern.chapter2.netty.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class WorkerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 工作线程处理逻辑
        ChannelPipeline p = ch.pipeline();
        // Netty 对http协议的封装，顺序有要求
        // HttpResponseEncoder 编码器
        p.addLast(new HttpResponseEncoder());
        // HttpRequestDecoder 解码器
        p.addLast(new HttpRequestDecoder());
        // 业务逻辑处理
        p.addLast().addLast(new GPTomcatHandler());

    }
}
