package com.ytq.design.pattern.netty.message.handler.chat;

import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.chat.ChatRedirectToUserRequest;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc: 客户端处理服务转发的消息
 * 2020-07-14
 */
@Component
public class ChatRedirectToUserRequestHandler implements MessageHandler<ChatRedirectToUserRequest> {
    private Logger logger = LoggerFactory.getLogger(ChatRedirectToUserRequestHandler.class);

    @Override
    public String getType() {
        return ChatRedirectToUserRequest.TYPE;
    }

    @Override
    public void execute(Channel channel, ChatRedirectToUserRequest message) {

        logger.info("服务端转发的消息：[{}]",message);

    }
}
