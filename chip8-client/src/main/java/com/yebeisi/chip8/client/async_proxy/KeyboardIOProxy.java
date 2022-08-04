package com.yebeisi.chip8.client.async_proxy;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.common.utils.FutureUtils;
import com.yebeisi.chip8.core.io.KeyboardIO;
import com.yebeisi.chip8.core.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class KeyboardIOProxy {

    @Autowired
    private KeyboardIO keyboardIO;

    @Autowired
    private InitService initService;

    public void pressKey(String sessionId, Keyboard.KeyEnum key) {
        FutureUtils.getResultOrNull(pressKeyAsync(sessionId, key));
    }

    public void releaseKey(String sessionId, Keyboard.KeyEnum key) {
        FutureUtils.getResultOrNull(releaseKeyAsync(sessionId, key));
    }

    @Async("ioTaskExecutor")
    public Future<Void> pressKeyAsync(String sessionId, Keyboard.KeyEnum key) {
        initService.initThreadLocally(sessionId);
        keyboardIO.pressKey(key);
        return new AsyncResult<>(null);
    }

    @Async("ioTaskExecutor")
    public Future<Void> releaseKeyAsync(String sessionId, Keyboard.KeyEnum key) {
        initService.initThreadLocally(sessionId);
        keyboardIO.releaseKey(key);
        return new AsyncResult<>(null);
    }

}
