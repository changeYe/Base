package com.ytq.design.pattern.netty.message.chat;

import com.ytq.design.pattern.netty.dispather.Message;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-14
 */
@Data
@Accessors(chain = true)
public class ChatSendToOneRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ONE_REQUEST";

    /**
     * 向谁发送消息
     */
    private String toUser;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 发送的内容
     */
    private String content;

}
