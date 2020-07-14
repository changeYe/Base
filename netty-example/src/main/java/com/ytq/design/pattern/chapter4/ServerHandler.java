package com.ytq.design.pattern.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-08
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 收到客户端写过来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String s = new String(bytes, "UTF-8");
        System.out.println("客户端返回的内容：" + s);

        // 回复客户端消息
        String server = "你好呀baby";
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(server.getBytes("UTF-8"));
        ctx.write(wrappedBuffer);

    }

    /**
     * flush：将消息发送队列中的消息写入到 SocketChannel 中发送给对方，为了频繁的唤醒 Selector 进行消息发送
     * Netty 的 write 方法并不直接将消息写如 SocketChannel 中，调用 write 只是把待发送的消息放到发送缓存数组中，再通过调用 flush
     * 方法，将发送缓冲区的消息全部写入到 SocketChannel 中
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
