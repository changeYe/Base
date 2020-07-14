package com.ytq.design.pattern.netty.client.handler;

import com.ytq.design.pattern.netty.client.NettyClient;
import com.ytq.design.pattern.netty.codec.Invocation;
import com.ytq.design.pattern.netty.message.heartbeat.HeartbeatRequest;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-10
 */
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private NettyClient nettyClient;

    /**
     * 实现在和服务端断开连接时，调用 NettyClient 的 #reconnect() 方法，实现客户端定时和服务端重连。
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 与服务端建立连接时，发起重连
        nettyClient.reconnect();
        // 继续触发事件
        super.channelActive(ctx);
    }

    /**
     * 在客户端在空闲时，向服务端发送一次心跳，即心跳机制。这块的内容，我们稍后详细讲讲。
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 空闲时，向服务端发起一次心跳
        if(evt instanceof IdleStateHandler){
            logger.info("[userEventTriggered][发起一次心跳]");
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            ctx.writeAndFlush(new Invocation(HeartbeatRequest.TYPE, heartbeatRequest))
                    .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    /**
     * 在处理 Channel 的事件发生异常时，调用 Channel 的 #close() 方法，断开和客户端的连接。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("[exceptionCaught][连接({}) 发生异常]", ctx.channel().id(), cause);
        // 断开连接
        ctx.channel().close();
    }
}
