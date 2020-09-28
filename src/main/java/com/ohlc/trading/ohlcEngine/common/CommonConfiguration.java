package com.ohlc.trading.ohlcEngine.common;

import com.ohlc.trading.ohlcEngine.queue.DefaultErrorHandler;
import com.ohlc.trading.ohlcEngine.queue.QueueMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class CommonConfiguration {

    @Autowired
    QueueMessageConverter queueMessageConverter;

    @Bean("queueContainerFactory")
    public JmsListenerContainerFactory<?> queueContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(new DefaultErrorHandler());
        factory.setMessageConverter(queueMessageConverter);
        return factory;
    }
}
