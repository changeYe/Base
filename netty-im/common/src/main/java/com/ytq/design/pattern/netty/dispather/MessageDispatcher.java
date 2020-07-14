package com.ytq.design.pattern.netty.dispather;

import com.alibaba.fastjson.JSON;
import com.ytq.design.pattern.netty.codec.Invocation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuantongqin
 * desc: 将 Invocation 分发到其对应的 MessageHandler 中，进行业务逻辑的执行。
 * SimpleChannelInboundHandler 是 Netty 定义的消息处理 ChannelHandler 抽象类，处理消息的类型是 <I> 泛型时。
 * 2020-07-09
 */
@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService executors = Executors.newFixedThreadPool(20);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation msg) throws Exception {
        // <3.1> 获得 type 对应的 MessageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(msg.getType());
        // 获得  MessageHandler 处理器的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // <3.2> 解析消息
        Message message = JSON.parseObject(msg.getMessage(), messageClass);
        // <3.3> 丢到线程池中，然后调用 MessageHandler 的 #execute(Channel channel, T message) 方法，执行业务逻辑。
        executors.submit(new Runnable() {
            @Override
            public void run() {
                // 具体的消息交给具体消息处理器处理
                messageHandler.execute(ctx.channel(),message);
            }
        });

    }
}
