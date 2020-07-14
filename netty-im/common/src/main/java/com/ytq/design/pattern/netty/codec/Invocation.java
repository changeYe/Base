package com.ytq.design.pattern.netty.codec;

import com.alibaba.fastjson.JSON;
import com.ytq.design.pattern.netty.dispather.Message;

/**
 * @author yuantongqin
 * desc: 通信协议的消息体
 * 2020-07-09
 */
public class Invocation {

    /**
     * type 属性，类型，用于匹配对应的消息处理器。
     */
    private String type;

    private String message;

    public Invocation() {
    }

    public Invocation(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public Invocation(String type, Message message) {
        this.type = type;
        this.message = JSON.toJSONString(message);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
