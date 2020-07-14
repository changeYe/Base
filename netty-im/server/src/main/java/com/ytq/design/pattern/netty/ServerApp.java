package com.ytq.design.pattern.netty;

import com.ytq.design.pattern.netty.server.NettyChannelManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-09
 */
@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServerApp.class, args);
        NettyChannelManager bean = context.getBean(NettyChannelManager.class);
        System.out.println(bean);
    }
}
