package com.yebeisi.chip8.core.service.context;

import com.yebeisi.chip8.common.data.Context;
import com.yebeisi.chip8.common.data.InstructionContext;
import com.yebeisi.chip8.common.data.Session;
import com.yebeisi.chip8.core.service.session.SessionService;
import com.yebeisi.chip8.logic.manager.ContextManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Service;

@Service
public class ContextServiceImpl implements ContextService {

    private static final ThreadLocal<Context> contextThreadLocal = new NamedThreadLocal<>("User Context");

    @Autowired
    private ContextManager contextManager;

    @Autowired
    private SessionService sessionService;

    @Override
    public void initContextThreadLocally() {
        Session session = sessionService.getSessionThreadLocally();
        String sessionId = session.getSessionId();
        Context context = contextManager.getContext(sessionId);
        contextThreadLocal.set(context);
    }

    @Override
    public Context getContext() {
        return contextThreadLocal.get();
    }

    @Override
    public InstructionContext getInstructionContext(String instructionName) {
        return contextThreadLocal.get().getInstructionContexts().get(instructionName);
    }
}
