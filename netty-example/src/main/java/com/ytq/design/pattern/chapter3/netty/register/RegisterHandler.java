package com.ytq.design.pattern.chapter3.netty.register;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
public class RegisterHandler extends ChannelInboundHandlerAdapter {

    // 与客户端建立连接
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        // 1、根据包名将所有符合条件的class扫描出来，放入容器中
        // 2、给每一个class取一个唯一的名字，作为服务名保存到一个容器中
        // 3、当有客户端访问过来的额时候，会先获取协议对象invokeProtocol对象
        // 4、去注册好的容器中，找到符合条件服务
        // 5、通过远程调用provider服务返回结果给客户端

        System.out.println("请求参数："+msg.toString());
        ctx.write("hello "+msg+"你好呀");
    }

    /**
     * 客户端执行，
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.flush();
    }

    /**
     * 可能出现异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
