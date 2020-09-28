package com.ohlc.trading.ohlcEngine.common;

import com.ohlc.trading.ohlcEngine.queue.DefaultErrorHandler;
import com.ohlc.trading.ohlcEngine.queue.QueueMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class CommonConfiguration {

    @Autowired
    QueueMessageConverter queueMessageConverter;

    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(new DefaultErrorHandler());
        factory.setMessageConverter(queueMessageConverter);
        return factory;
    }
}
