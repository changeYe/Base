package com.ytq.design.pattern.netty.message.handler.auth;

import com.ytq.design.pattern.netty.codec.Invocation;
import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.auth.AuthRequest;
import com.ytq.design.pattern.netty.message.auth.AuthResponse;
import com.ytq.design.pattern.netty.server.NettyChannelManager;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author yuantongqin
 * desc: 认证消息处理
 * 2020-07-13
 */
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Autowired
    NettyChannelManager nettyChannelManager;

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }

    @Override
    public void execute(Channel channel, AuthRequest message) {
        // <1> 如果未传递 accessToken
        if(StringUtils.isEmpty(message.getAccessToken())){
            AuthResponse response = new AuthResponse();
            response.setCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("认证 accessToken 未传入");
            channel.writeAndFlush(new Invocation(AuthResponse.TYPE,response));
            return;
        }


        // ... 此处应有一段

        // 将用户和 Channel 绑定
        // 考虑到代码简化，我们先直接使用 accessToken 作为 User
        nettyChannelManager.addUser(channel, message.getAccessToken());

        // 响应认证成功
        AuthResponse response = new AuthResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("认证成功");
        channel.writeAndFlush(new Invocation(AuthResponse.TYPE,response));

    }
}
