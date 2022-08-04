package com.yebeisi.chip8.client.async_proxy;

import com.yebeisi.chip8.common.utils.FutureUtils;
import com.yebeisi.chip8.core.io.GraphIO;
import com.yebeisi.chip8.core.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class GraphIOProxy {

    @Autowired
    private GraphIO graphIO;

    @Autowired
    private InitService initService;

    public int[][] getGraphMemory(String sessionId) {
        return FutureUtils.getResultOrNull(getGraphMemoryAsync(sessionId));
    }

    @Async("ioTaskExecutor")
    public Future<int[][]> getGraphMemoryAsync(String sessionId) {
        initService.initThreadLocally(sessionId);
        return new AsyncResult<>(graphIO.getGraphMemory());
    }

}
