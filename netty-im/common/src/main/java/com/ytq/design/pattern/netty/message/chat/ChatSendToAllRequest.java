package com.ytq.design.pattern.netty.message.chat;

import com.ytq.design.pattern.netty.dispather.Message;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuantongqin
 * desc: 转发所有人
 * 2020-07-14
 */
@Data
@Accessors(chain = true)
public class ChatSendToAllRequest implements Message {
    public static final String TYPE = "CHAT_SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
}
