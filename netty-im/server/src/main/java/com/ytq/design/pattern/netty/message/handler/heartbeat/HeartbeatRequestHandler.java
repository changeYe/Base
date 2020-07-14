package com.ytq.design.pattern.netty.message.handler.heartbeat;

import com.ytq.design.pattern.netty.codec.Invocation;
import com.ytq.design.pattern.netty.dispather.MessageHandler;
import com.ytq.design.pattern.netty.message.heartbeat.HeartbeatRequest;
import com.ytq.design.pattern.netty.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-13
 */
public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getType() {
        return HeartbeatRequest.TYPE;
    }

    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        // 响应心跳
        HeartbeatResponse response = new HeartbeatResponse();
        Invocation invocation = new Invocation(HeartbeatResponse.TYPE,response);
        channel.writeAndFlush(invocation);
    }
}
