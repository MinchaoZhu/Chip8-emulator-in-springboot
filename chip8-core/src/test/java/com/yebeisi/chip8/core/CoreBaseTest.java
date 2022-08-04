package com.yebeisi.chip8.core;

import com.yebeisi.chip8.core.service.InitService;
import com.yebeisi.chip8.core.service.session.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

public abstract class CoreBaseTest {

    @Autowired
    protected InitService initService;

    @Autowired
    protected SessionService sessionService;

    @BeforeEach
    public void init() {
        Future<Boolean> future = initService.initContext(sessionService.getSessionThreadLocally().getSessionId());
        while(!future.isDone()) {}
    }

}
