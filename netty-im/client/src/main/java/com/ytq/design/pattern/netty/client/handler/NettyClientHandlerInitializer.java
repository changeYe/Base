package com.ytq.design.pattern.netty.client.handler;

import com.ytq.design.pattern.netty.codec.InvocationDecoder;
import com.ytq.design.pattern.netty.codec.InvocationEncoder;
import com.ytq.design.pattern.netty.dispather.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-09
 */
@Component
public class NettyClientHandlerInitializer extends ChannelInitializer<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyClientHandlerInitializer.class);
    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 60;

    @Autowired
    private NettyClientHandler nettyClientHandler;
    @Autowired
    private MessageDispatcher dispatcher;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        // 空闲检测与超时检测
        channel.pipeline()
                .addLast(new IdleStateHandler(READ_TIMEOUT_SECONDS,0,0))
                // 实现客户端发现 180 秒未从服务端读取到消息，主动断开连接。
                .addLast(new ReadTimeoutHandler(3 * READ_TIMEOUT_SECONDS))
                // 编码
                .addLast(new InvocationEncoder())
                // 解码
                .addLast(new InvocationDecoder())
                // 消息分发
                .addLast(dispatcher)
                // 消息处理
                .addLast(nettyClientHandler);
    }
}
