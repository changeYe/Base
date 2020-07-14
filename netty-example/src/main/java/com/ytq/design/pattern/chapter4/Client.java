package com.ytq.design.pattern.chapter4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-08
 */
public class Client {


    public static void main(String[] args) {
        new Client().start();
    }

    public void start() {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new ClientHandler());
                }
            });
            /**connect：发起异步连接操作，调用同步方法 sync 等待连接成功*/
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8080).sync();
            System.out.println("建立连接");
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        }finally {
            workGroup.shutdownGracefully();
        }



    }
}
