package com.yebeisi.chip8.core;

import com.yebeisi.chip8.core.component.controller.TimerController;
import com.yebeisi.chip8.core.component.descriptor.Descriptor;
import com.yebeisi.chip8.core.service.InitService;
import com.yebeisi.chip8.core.service.session.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Executor {

    @Autowired
    private TimerController timerController;

    @Autowired
    private Descriptor descriptor;

    @Autowired
    private InitService initService;

    @Autowired
    private SessionService sessionService;

    @Async("coreTaskExecutor")
    public void execute(String sessionId) {
        initService.initThreadLocally(sessionId);
        try {
            descriptor.execute();
        } catch(Exception e) {
            sessionService.getSession(sessionId).setError(e);
        }
        timerController.timerDecrease();
    }
}
