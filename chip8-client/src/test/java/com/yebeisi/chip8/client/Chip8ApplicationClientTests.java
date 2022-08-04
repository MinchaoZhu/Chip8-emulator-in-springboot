package com.yebeisi.chip8.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootConfiguration
@ComponentScan({"com.yebeisi.chip8.**"})
@EnableAsync
class Chip8ApplicationClientTests {

    public static void main(String[] args) {
        SpringApplication.run(Chip8ApplicationClientTests.class, args);
    }

}
