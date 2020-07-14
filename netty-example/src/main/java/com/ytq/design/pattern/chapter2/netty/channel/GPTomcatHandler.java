package com.ytq.design.pattern.chapter2.netty.channel;

import com.ytq.design.pattern.chapter2.netty.GPTomcat;
import com.ytq.design.pattern.chapter2.netty.http.GPRequest;
import com.ytq.design.pattern.chapter2.netty.http.GPResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-04
 */
public class GPTomcatHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){

            HttpRequest httpRequest = (HttpRequest) msg;

            //创建自己request，进行处理
            GPRequest request = new GPRequest(ctx,httpRequest);

            //创建自己Response，进行处理
            GPResponse response = new GPResponse(ctx,httpRequest);

            String url = request.getUrl();

            if (GPTomcat.servletMapping.containsKey(url)) {
                GPTomcat.servletMapping.get(url).service(request,response);
            }else{
                response.write("404 not found");
            }


        }
    }
}
