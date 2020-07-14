package com.ytq.design.pattern.netty.message.handler.chat;

import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.chat.ChatSendResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-14
 */
@Component
public class ChatSendResponseHandler implements MessageHandler<ChatSendResponse> {
    private Logger logger = LoggerFactory.getLogger(ChatSendResponseHandler.class);

    @Override
    public String getType() {
        return ChatSendResponse.TYPE;
    }

    @Override
    public void execute(Channel channel, ChatSendResponse message) {

        logger.info("接受到服务返回的消息：[{}]",message);


    }
}
