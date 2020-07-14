package com.ytq.design.pattern.netty.message.handler.chat;

import com.ytq.design.pattern.netty.codec.Invocation;
import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.chat.ChatRedirectToUserRequest;
import com.ytq.design.pattern.netty.message.chat.ChatSendResponse;
import com.ytq.design.pattern.netty.message.chat.ChatSendToOneRequest;
import com.ytq.design.pattern.netty.server.NettyChannelManager;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-14
 */
@Component
public class ChatSendToOneHandler implements MessageHandler<ChatSendToOneRequest> {
    private Logger logger = LoggerFactory.getLogger(ChatSendToOneHandler.class);

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public String getType() {
        return ChatSendToOneRequest.TYPE;
    }

    @Override
    public void execute(Channel channel, ChatSendToOneRequest message) {
        logger.info("客户端：发送过来的消息，[{}]", message);
        if (message == null) {
            return;
        }
        // 回复点什么内容
        ChatSendResponse response = new ChatSendResponse();
        response.setMsgId(message.getMsgId())
                .setCode(HttpStatus.OK.value()).setMessage("服务端接受成功了");
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));

        // 消息转发给指定用户
        ChatRedirectToUserRequest redirect = new ChatRedirectToUserRequest();
        redirect.setMsgId(message.getMsgId())
                .setContent(message.getContent())
                .setFromUser(message.getToUser());
        nettyChannelManager.send(message.getToUser(), new Invocation(ChatRedirectToUserRequest.TYPE, redirect));

    }
}
