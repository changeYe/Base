package com.ytq.design.pattern.netty.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yuantongqin
 * desc: 解码，进行反序列化
 * 实现从 TCP Socket 读取字节数组，反序列化成 Invocation。
 * ByteToMessageDecoder 是 Netty 定义的解码 ChannelHandler 抽象类，在 TCP Socket 读取到新数据时，触发进行解码。
 * 2020-07-09
 */
public class InvocationDecoder extends ByteToMessageDecoder {
    private Logger logger = LoggerFactory.getLogger(InvocationDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 标记当前读取位置
        in.markReaderIndex();
        // 判断是否能够读取 length 长度
        if (in.readableBytes() <= 4) {
            return;
        }
        // 读取长度
        int length = in.readInt();
        if (length < 0) {
            throw new CorruptedFrameException("negative length: " + length);
        }
        // 如果 message 不够可读(客户端数据没有完全发送完成)，则退回到原读取位置
        if(in.readableBytes() <length){
            in.resetReaderIndex();
            return;
        }
        byte[] content = new byte[length];
        in.readBytes(content);
        // 解析成 Invocation
        Invocation invocation = JSON.parseObject(content, Invocation.class);
        out.add(invocation);
        logger.info("[decode][连接({}) 解析到一条消息({})]", ctx.channel().id(), invocation.toString());

    }
}
