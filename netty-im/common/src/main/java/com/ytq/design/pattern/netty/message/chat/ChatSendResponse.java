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
public class ChatSendResponse implements Message {

    public static final String TYPE = "CHAT_SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;
}
