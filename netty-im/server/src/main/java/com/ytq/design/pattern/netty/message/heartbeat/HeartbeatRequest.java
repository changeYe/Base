package com.ytq.design.pattern.netty.message.heartbeat;

import com.ytq.design.pattern.netty.dispather.Message;

/**
 * @author yuantongqin
 * desc: 消息 - 心跳请求
 * 2020-07-13
 */
public class HeartbeatRequest implements Message {

    /**
     * 类型 - 心跳请求
     */
    public static final String TYPE = "HEARTBEAT_REQUEST";

    @Override
    public String toString() {
        return "HeartbeatRequest{}";
    }


}
