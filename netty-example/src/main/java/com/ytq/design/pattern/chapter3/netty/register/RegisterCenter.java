package com.ytq.design.pattern.chapter3.netty.register;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
public class RegisterCenter {

    public int port = 8080;

    public void start() {

        //boss线程
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        // work线程
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 在netty中，把所有的业务逻辑全部归总到一个队列中
                            // 封装成一个对象，无锁化串行话处理
                            ChannelPipeline pipeline = ch.pipeline();

                            // 对自定义协议进行解码
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            // 编码
                            pipeline.addLast(new LengthFieldPrepender(4));
                            // 参数处理
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            // 编解码进行数据的解析，这里是执行逻辑
                            pipeline.addLast(new RegisterHandler());

                        }
                    });
            // 开启一个死循环进行监控
            ChannelFuture future = serverBootstrap.bind(this.port).sync();
            System.out.println("启动成功："+this.port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new RegisterCenter().start();
    }
}
