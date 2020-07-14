package com.ytq.design.pattern.netty.server;

import com.ytq.design.pattern.netty.server.handler.NettyServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yuantongqin
 * desc: Netty采用的是Reactor多线程模型，可以同时接受多个客户端的连接与数据读取；
 * 2020-07-09
 */
@Component
public class NettyServer {

    Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Value("${netty.port}")
    private int port;
    private Channel channel;
    /**
     * boss 线程组，用于服务端接受客户端的连接
     */
    private EventLoopGroup boosGroup = new NioEventLoopGroup();
    /**
     * work线程组，用于服务端接受客户端的数据读写
     */
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    @Autowired
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;
    /**
     * 启动 Netty 服务器。
     */
    @PostConstruct
    public void start(){

        try {
            // 启动程序入口
            ServerBootstrap server = new ServerBootstrap();
            server.group(boosGroup,workGroup)
                    // 它是 Netty 定义的 NIO 服务端 TCP Socket 实现类
                    .channel(NioServerSocketChannel.class)
                    // 服务端 accept 队列的大小，设置服务端接受客户端的连接队列大小。
                    // 因为 TCP 建立连接是三次握手，所以第一次握手完成后，会添加到服务端的连接队列中。
                    .option(ChannelOption.SO_BACKLOG,1024)
                    // TCP KeepAlive 机制，实现 TCP 层级的心跳保活功能
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    // 允许较小的数据包的发送，降低延迟
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(nettyServerHandlerInitializer);
            // 绑定端口，并同步等待成功，即启动服务端
            ChannelFuture future = server.bind(this.port).sync();
            if(future.isSuccess()){
                channel = future.channel();
                logger.info("[start][Netty Server 启动在 {} 端口]", port);
            }

        }catch (Exception e){
            logger.error("启动异常",e);
        }

    }

    /**
     * 关闭 Netty 服务器。
     */
    @PreDestroy
    public void destroy(){
       // 关闭 Netty Server，这样客户端就不再能连接了。
        if(channel != null){
            channel.close();
        }
       // 优雅关闭两个 EventLoopGroup 对象 ，如关闭它们里面的线程池。
        boosGroup.shutdownGracefully();
        workGroup.shutdownGracefully();

    }

}
