package com.ytq.design.pattern.netty.server;

import com.ytq.design.pattern.netty.codec.Invocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yuantongqin
 * desc: 客户端 Channel 管理器。提供两种功能：
 * 1. 客户端 Channel 的管理
 * 2. 向客户端 Channel 发送消息
 * 2020-07-09
 */
@Component
public class NettyChannelManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * CHANNEL_ATTR_KEY_USER属性中，表示 Channel 对应的用户
     */
    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER = AttributeKey.newInstance("user");

    /**
     * channel 映射,每一个channel对应一个唯一ID
     */
    private ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();

    /**
     * 用户与 Channel 的映射。
     * 通过它，可以获取用户对应的 Channel。这样，我们可以向指定用户发送消息。
     */
    private ConcurrentMap<String, Channel> userChannel = new ConcurrentHashMap<>();


    public void add(Channel channel) {
        channels.put(channel.id(), channel);
        logger.info("添加一个新的channel:[{}]", channel.id());
    }

    /**
     * 添加指定用户
     */
    public void addUser(Channel channel, String user) {
        Channel existChannel = channels.get(channel.id());
        if(existChannel == null){
            logger.warn("channel信息不存在：[{}]",channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 添加到userChannel中
        userChannel.put(user,channel);

    }

    public void remove(Channel channel) {
        if (channel == null) {
            return;
        }
        channels.remove(channel.id());
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)) {
            userChannel.remove(channel.attr(CHANNEL_ATTR_KEY_USER).get());
        }

        logger.info("移除一个channel:[{}]", channel.id());

    }

    /**
     * 向指定用户发送消息
     */
    public void send(String user, Invocation invocation){
        Channel channel = userChannel.get(user);
        if(Objects.isNull(channel)){
            logger.warn("channel为null，不能发送消息：[{}]",user);
            return;
        }
        if(!channel.isActive()){
            logger.warn("channel为未激活，不能发送消息：[{}]",user);
            return;
        }
        // 发送消息
        channel.writeAndFlush(invocation);

    }

    /**
     * 向所有用户发送消息
     */
    public void sendAll(Invocation invocation){
        channels.values().forEach(info->{
            if (!info.isActive()) {
                logger.error("[send][连接({})未激活]", info.id());
                return;
            }
            // 发送消息
            info.writeAndFlush(invocation);
        });
    }

}
