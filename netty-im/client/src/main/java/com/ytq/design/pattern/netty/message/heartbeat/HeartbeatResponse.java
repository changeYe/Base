package com.ytq.design.pattern.netty.message.heartbeat;

import com.ytq.design.pattern.netty.dispather.Message;

/**
 * @author yuantongqin
 * desc: 消息-心跳响应
 * 2020-07-13
 */
public class HeartbeatResponse implements Message {

    /**
     * 类型 - 心跳响应
     */
    public static final String TYPE = "HEARTBEAT_RESPONSE";

    @Override
    public String toString() {
        return "HeartbeatResponse{}";
    }
}
