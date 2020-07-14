package com.ytq.design.pattern.netty.message.handler.auth;

import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.auth.AuthResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-13
 */
@Component
public class AuthResponseHandler implements MessageHandler<AuthResponse> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getType() {

        return AuthResponse.TYPE;
    }

    @Override
    public void execute(Channel channel, AuthResponse message) {
        logger.info("认证结果：[{}]",message);
    }
}
