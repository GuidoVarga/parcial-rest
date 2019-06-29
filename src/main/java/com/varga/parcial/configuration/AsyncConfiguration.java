package com.varga.parcial.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncConfiguration {

    @Value("${executor.corePoolSize}")
    private Integer CORE_POOL_SIZE;
    @Value("${executor.maxPoolSize}")
    private Integer MAX_POOL_SIZE;
    @Value("${executor.queueCapacity")
    private Integer QUEUE_CAPACITY;

    @Bean("threadPoolTaskExecutor")
    public Executor asyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.initialize();
        return executor;
    }
}
