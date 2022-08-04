package com.yebeisi.chip8.core.service;

import com.yebeisi.chip8.core.component.controller.GraphController;
import com.yebeisi.chip8.core.component.controller.MemoryController;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.controller.StackController;
import com.yebeisi.chip8.core.component.controller.TimerController;
import com.yebeisi.chip8.core.service.context.ContextService;
import com.yebeisi.chip8.core.service.session.SessionService;
import com.yebeisi.chip8.logic.manager.ContextManager;
import com.yebeisi.chip8.logic.manager.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class InitService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ContextService contextService;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ContextManager contextManager;

    @Autowired
    private GraphController graphController;

    @Autowired
    private MemoryController memoryController;

    @Autowired
    private RegisterController registerController;

    @Autowired
    private StackController stackController;

    @Autowired
    private TimerController timerController;

    public void initThreadLocally(String sessionId) {
        sessionService.initSessionThreadLocally(sessionId);
        contextService.initContextThreadLocally();
    }

    public String createNewSession(String user) {
        String sessionId =  sessionManager.registerSession(user);
        contextManager.registerContext(sessionId);
        return sessionId;
    }

    @Async("dispatcherTaskExecutor")
    public Future<Boolean> initContext(String sessionId) {
        initThreadLocally(sessionId);
        graphController.initGraph();
        memoryController.initMemory();
        registerController.initRegister();
        stackController.initStack();
        timerController.initTimer();
        return CompletableFuture.completedFuture(true);
    }

    public void removeSession(String sessionId) {
        contextManager.unregisterContext(sessionService.getSession(sessionId).getContextKey());
        sessionManager.unregisterSession(sessionId);
    }

}
