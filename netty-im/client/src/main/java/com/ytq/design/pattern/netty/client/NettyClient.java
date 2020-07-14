package com.ytq.design.pattern.netty.client;

import com.ytq.design.pattern.netty.client.handler.NettyClientHandlerInitializer;
import com.ytq.design.pattern.netty.codec.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author yuantongqin
 * desc: 至此，我们已经构建 Netty 服务端和客户端完成。
 * 因为 Netty 提供的 API 非常便利，所以我们不会像直接使用 NIO 时，需要处理大量底层且细节的代码。
 * 2020-07-09
 */
@Component
public class NettyClient {
    private Logger logger = LoggerFactory.getLogger(NettyClient.class);
    /**
     * 重连频率，单位：秒
     */
    private static final Integer RECONNECT_SECONDS = 20;
    @Value("${netty.port}")
    private int port;

    @Value("${netty.hostName}")
    private String hostName;

    @Autowired
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;
    private volatile Channel channel;
    private EventLoopGroup workGroup = new NioEventLoopGroup();


    @PostConstruct
    public void start(){

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(nettyClientHandlerInitializer);
        // 链接服务器，并异步等待成功，即启动客户端
        bootstrap.connect(hostName,port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                // 连接失败
                if(!channelFuture.isSuccess()){
                    logger.error("[start][Netty Client 连接服务器({}:{}) 失败]", hostName, port);
                    reconnect();
                    return;
                }
                // 成功
                channel = channelFuture.channel();
                logger.info("[start][Netty Client 连接服务器({}:{}) 成功]", hostName, port);
            }

        });
    }

    public void reconnect() {
        workGroup.schedule(() -> {
            try {
                start();
            }catch (Exception e){
               logger.error("重连失败：",e);
            }

        },RECONNECT_SECONDS, TimeUnit.SECONDS);
        logger.info("[reconnect][{} 秒后将发起重连]", RECONNECT_SECONDS);
    }

    @PreDestroy
    public void destroy(){
        if (channel != null) {
            channel.close();
        }
        // 优雅关闭一个 EventLoopGroup 对象
        workGroup.shutdownGracefully();
    }

    /**
     * 发送消息
     */
    public void send(Invocation invocation){
        if(channel == null){
            logger.error("未连接");
            return;
        }
        if(!channel.isActive()){
            logger.error("未连接");
            return;
        }
        channel.writeAndFlush(invocation);
    }
}
