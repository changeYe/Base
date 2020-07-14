package com.ytq.design.pattern.chapter2.netty;

import com.ytq.design.pattern.chapter2.netty.channel.WorkerChannelInitializer;
import com.ytq.design.pattern.chapter2.netty.http.GPServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class GPTomcat {

    private int port = 8080;

    public static Map<String, GPServlet> servletMapping = new HashMap<>();

    Properties webXml = new Properties();

    /**
     * 初始化
     */
    private void init() {
        InputStream resource = this.getClass().getResourceAsStream("/web.properties");
        try {
            webXml.load(resource);

            webXml.keySet().forEach(info -> {
                String key;
                if (info != null && info.toString().endsWith("url")) {
                    key = info.toString();
                    String url = webXml.getProperty(key);
                    String urlPrefix = key.replaceAll("\\.url$", "");
                    String className = webXml.getProperty(urlPrefix + ".class");
                    try {
                        GPServlet gpServlet = (GPServlet) Class.forName(className).newInstance();
                        servletMapping.put(url, gpServlet);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void start() {

        init();

        // Netty 封装了NIO,Reactor模型,Boss/worker线程
        // 设置boss线程数,
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 设置work线程数
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // ServerBootstrap与java原生ServerSocketChannel 是1对1关系，进行通信使用
            ServerBootstrap bootstrap = new ServerBootstrap();

//            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.group(bossGroup, workerGroup)
                    // 负责轮训所有的请求线程
                    .channel(NioServerSocketChannel.class)
                    // 主线程处理类
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 工作线程处理类
                    .childHandler(new WorkerChannelInitializer())
                    // 针对主线程的配置，分配最大主线程数量128
                    .option(ChannelOption.SO_BACKLOG,128)
                    // 子线程的配置，保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            // 启动服务
            ChannelFuture sync = bootstrap.bind(port).sync();
            System.out.println("服务启动成功："+port);
            sync.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
