package com.yebeisi.chip8.logic.manager;

import com.yebeisi.chip8.common.data.Context;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ContextManager {

    private static final Map<String, Context> contexts = new ConcurrentHashMap<>();

    public Map<String, Context> getContexts() {
        return contexts;
    }

    public Context getContext(String key) {
        return contexts.get(key);
    }

    public boolean contextExist(String key) {
        return contexts.containsKey(key);
    }

    public void registerContext(String key) {
        contexts.put(key, new Context());
    }

    public void unregisterContext(String key) {
        contexts.remove(key);
    }

}
