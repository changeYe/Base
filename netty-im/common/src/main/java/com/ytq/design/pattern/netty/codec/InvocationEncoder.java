package com.ytq.design.pattern.netty.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuantongqin
 * desc: 编码 进行对象序列化
 * MessageToByteEncoder 是 Netty 定义的编码 ChannelHandler 抽象类，将泛型 <I> 消息转换成字节数组。
 * 友情提示：MessageToByteEncoder 会最终将 ByteBuf 写到 TCP Socket 中。
 * 2020-07-09
 */
public class InvocationEncoder extends MessageToByteEncoder<Invocation> {

    private Logger logger = LoggerFactory.getLogger(InvocationEncoder.class);
    @Override
    protected void encode(ChannelHandlerContext ctx, Invocation msg, ByteBuf out) throws Exception {
        // 将 Invocation 转换成 byte[] 数组
        byte[] bytes = JSON.toJSONBytes(msg);
        // 将字节数组的长度，写入到 TCP Socket 当中。
        // 这样，后续「3.4 InvocationDecoder」可以根据该长度，解析到消息，解决粘包和拆包的问题。
        out.writeInt(bytes.length);
        // 写入内容
        out.writeBytes(bytes);
        logger.info("[encode][连接({}) 编码了一条消息({})]", ctx.channel().id(), msg.toString());

    }
}
