package com.ytq.design.pattern.chapter3.netty.consumer;

import com.ytq.design.pattern.chapter3.netty.register.RegisterHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
public class ConsumerService {


    public static void main(String[] args) {

        // 发送一个简单的消息给服务端，服务端进行返回
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
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
                    pipeline.addLast(new ConsumerHandler());
                }
            });

            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();
            String hello = "changeHOOL";
            future.channel().writeAndFlush(hello).sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}
