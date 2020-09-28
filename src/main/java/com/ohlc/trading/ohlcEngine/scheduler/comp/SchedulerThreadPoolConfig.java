/*
package com.ohlc.trading.ohlcEngine.scheduler.comp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
public class SchedulerThreadPoolConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerThreadPoolConfig.class);

    @Value("${pool.size.core}")
    int corePoolSize;

    @Value("${pool.size.max}")
    int maxPoolSize;

    @Value("${queue.size.max}")
    int maxQueueSize;

    @Bean(name = "readTradeExecutor")
    public Executor readTradeTaskExecutor(){
        LOGGER.info("### readTradeTaskExecutor() ###");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(maxQueueSize);
        threadPoolTaskExecutor.setThreadNamePrefix("readTradeExecutor - ");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean(name = "finiteStateMachineExecutor")
    public Executor finiteStateMachineExecutor(){
        LOGGER.info("### finiteStateMachineExecutor() ###");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(maxQueueSize);
        threadPoolTaskExecutor.setThreadNamePrefix("finiteStateMachineExecutor - ");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean(name = "emitterExecutor")
    public Executor emitterExecutor(){
        LOGGER.info("### finiteStateMachineExecutor() ###");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(maxQueueSize);
        threadPoolTaskExecutor.setThreadNamePrefix("emitterExecutor - ");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
*/
