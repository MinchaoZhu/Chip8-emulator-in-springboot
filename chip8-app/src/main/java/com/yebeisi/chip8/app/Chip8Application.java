package com.yebeisi.chip8.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan({"com.yebeisi.chip8.**"})
public class Chip8Application {

    public static void main(String[] args) {
        SpringApplication.run(Chip8Application.class, args);
    }

}
