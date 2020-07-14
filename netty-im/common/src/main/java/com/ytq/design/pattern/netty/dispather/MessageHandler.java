package com.ytq.design.pattern.netty.dispather;

import io.netty.channel.Channel;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-09
 */
public interface MessageHandler<T extends Message> {

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();

    /**
     * 执行处理消息
     *
     * @param channel 通道
     * @param message 消息
     */
    void execute(Channel channel, T message);
}
