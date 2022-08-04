package com.yebeisi.chip8.core.service.session;

import com.yebeisi.chip8.common.data.Session;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {

    void initSessionThreadLocally(String sessionId);

    Session getSessionThreadLocally();

    Session getSession(String sessionId);

    boolean sessionExists(String sessionId);

}
