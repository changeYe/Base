package com.ytq.design.pattern.netty.message.auth;

import com.ytq.design.pattern.netty.dispather.Message;
import lombok.Data;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-09
 */
@Data
public class AuthRequest implements Message {

    public final static String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;
}
