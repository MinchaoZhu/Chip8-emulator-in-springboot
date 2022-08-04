package com.yebeisi.chip8.core;

import com.yebeisi.chip8.core.service.InitService;
import com.yebeisi.chip8.core.service.session.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootConfiguration
@ComponentScan({"com.yebeisi.chip8.**"})
@EnableAsync
class Chip8ApplicationTests {

    @Autowired
    InitService initService;

    @Autowired
    SessionService sessionService;


    public static void main(String[] args) {
        SpringApplication.run(Chip8ApplicationTests.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setUpSession() {
        String sessionId = initService.createNewSession("test");
        initService.initThreadLocally(sessionId);
    }

    @BeforeEach
    public void init() {
        initService.initContext(sessionService.getSessionThreadLocally().getSessionId());
    }

}
