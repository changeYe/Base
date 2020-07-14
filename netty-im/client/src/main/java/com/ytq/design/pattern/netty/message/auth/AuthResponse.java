package com.ytq.design.pattern.netty.message.auth;

import com.ytq.design.pattern.netty.dispather.Message;
import lombok.Data;

/**
 * @author yuantongqin
 * desc: 消息-响应
 * 2020-07-13
 */
@Data
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;


}
