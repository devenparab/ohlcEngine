package com.ohlc.trading.ohlcEngine.common;

import com.ohlc.trading.ohlcEngine.queue.DefaultErrorHandler;
import com.ohlc.trading.ohlcEngine.queue.QueueMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executor;

@Configuration
@EnableJms
public class CommonConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonConfiguration.class);

    @Autowired
    QueueMessageConverter queueMessageConverter;

    @Value("${pool.size.core}")
    int corePoolSize;

    @Value("${pool.size.max}")
    int maxPoolSize;

    @Value("${queue.size.max}")
    int maxQueueSize;

    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(new DefaultErrorHandler());
        factory.setMessageConverter(queueMessageConverter);
        return factory;
    }

    @Bean(name = "workerFiniteStateMachineExecutor")
    public Executor workerFiniteStateMachineExecutor(){
        LOGGER.info("### workerFiniteStateMachineExecutor() ###");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(maxQueueSize);
        threadPoolTaskExecutor.setThreadNamePrefix("workerFiniteStateMachineExecutor - ");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean(name = "workerChartingMachineExecutor")
    public Executor workerChartingMachineExecutor(){
        LOGGER.info("### workerFiniteStateMachineExecutor() ###");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(maxQueueSize);
        threadPoolTaskExecutor.setThreadNamePrefix("workerChartingMachineExecutor - ");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
