package com.ytq.design.pattern.netty.message.handler.chat;

import com.ytq.design.pattern.netty.codec.Invocation;
import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.chat.ChatRedirectToUserRequest;
import com.ytq.design.pattern.netty.message.chat.ChatSendResponse;
import com.ytq.design.pattern.netty.message.chat.ChatSendToAllRequest;
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
public class ChatSendToAllHandler implements MessageHandler<ChatSendToAllRequest> {
    private Logger logger = LoggerFactory.getLogger(ChatSendToAllHandler.class);

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public String getType() {
        return ChatSendToAllRequest.TYPE;
    }

    @Override
    public void execute(Channel channel, ChatSendToAllRequest message) {
        logger.info("消息群发：[{}]", message);
        if (message == null) {
            return;
        }
        ChatSendResponse response = new ChatSendResponse();
        response.setMessage("群发消息成功")
                .setMsgId(message.getMsgId()).setCode(HttpStatus.OK.value());
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE,response));

        // 群发
        ChatRedirectToUserRequest request = new ChatRedirectToUserRequest();
        request.setMsgId(message.getMsgId()).setContent(message.getContent());
        nettyChannelManager.sendAll(new Invocation(ChatSendToOneRequest.TYPE,request));
    }
}
