package com.yebeisi.chip8.logic.manager;

import com.yebeisi.chip8.common.data.Session;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    Map<String, Session> getSessions() {
        return sessions;
    }

    public boolean sessionExists(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public String registerSession(String user) {
        String sessionId = UUID.randomUUID().toString();
        Session session = Session.builder()
                .sessionId(sessionId)
                .user(user)
                .status(Session.NEW)
                .acceleration(1.0)
                .contextKey(sessionId)
                .isErrored(false)
                .build();
        sessions.put(sessionId, session);
        return sessionId;
    }

    public void unregisterSession(String sessionId) {
        sessions.remove(sessionId);
    }

}
