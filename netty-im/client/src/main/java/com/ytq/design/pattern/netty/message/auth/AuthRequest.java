package com.ytq.design.pattern.netty.message.auth;

import com.ytq.design.pattern.netty.dispather.Message;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuantongqin
 * desc: 消息-认证
 * 2020-07-13
 */
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    @Getter
    @Setter
    private String accessToken;


}
