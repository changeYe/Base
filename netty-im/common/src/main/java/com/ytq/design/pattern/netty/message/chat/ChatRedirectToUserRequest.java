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
public class ChatRedirectToUserRequest implements Message {

    public static final String TYPE = "CHAT_REDIRECT_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    /**
     * 来自谁的消息
     */
    private String fromUser;
}
