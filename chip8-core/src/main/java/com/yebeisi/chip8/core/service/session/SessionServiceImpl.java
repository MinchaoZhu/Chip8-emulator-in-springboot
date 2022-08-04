package com.yebeisi.chip8.core.service.session;

import com.yebeisi.chip8.common.data.Session;
import com.yebeisi.chip8.logic.manager.SessionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService{

    private static final ThreadLocal<Session> sessionThreadLocal = new NamedThreadLocal<>("User Session");

    @Autowired
    private SessionManager sessionManager;

    @Override
    public void initSessionThreadLocally(String sessionId) {

        if(StringUtils.isBlank(sessionId) || !sessionManager.sessionExists(sessionId)) {
            throw new RuntimeException("session id cannot be empty");
        }

        sessionThreadLocal.set(sessionManager.getSession(sessionId)); // set session
    }

    @Override
    public Session getSessionThreadLocally() {
        return sessionThreadLocal.get();
    }

    @Override
    public Session getSession(String sessionId) {
        return sessionManager.getSession(sessionId);
    }

    @Override
    public boolean sessionExists(String sessionId) {
        return sessionManager.sessionExists(sessionId);
    }

}
