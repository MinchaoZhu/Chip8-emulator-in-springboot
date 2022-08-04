package com.yebeisi.chip8.logic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorManagerConfig {

    @Bean("dispatcherTaskExecutor")
    public TaskExecutor dispatcherTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("dispatcher-");

        return taskExecutor;
    }

    @Bean("coreTaskExecutor")
    public TaskExecutor coreTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setKeepAliveSeconds(20);
        taskExecutor.setThreadNamePrefix("core-");

        return taskExecutor;
    }

    @Bean("ioTaskExecutor")
    public TaskExecutor ioTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(40);
        taskExecutor.setKeepAliveSeconds(1);
        taskExecutor.setThreadNamePrefix("io-");

        return taskExecutor;
    }

}
