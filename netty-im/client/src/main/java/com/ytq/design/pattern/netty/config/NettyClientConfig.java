package com.ytq.design.pattern.netty.config;

import com.ytq.design.pattern.netty.dispather.MessageDispatcher;
import com.ytq.design.pattern.netty.dispather.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-10
 */
@Configuration
public class NettyClientConfig {

    /**
     * 消息分发器
     * @return
     */
    @Bean
    public MessageDispatcher messageDispatcher(){
        return new MessageDispatcher();
    }

    /**
     * 消息处理容器
     * @return
     */
    @Bean
    public MessageHandlerContainer messageHandlerContainer(){
        return new MessageHandlerContainer();
    }
}
