package com.yebeisi.chip8.client.async_proxy;

import com.yebeisi.chip8.common.utils.FutureUtils;
import com.yebeisi.chip8.core.io.DataReader;
import com.yebeisi.chip8.core.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class DataReaderProxy {

    @Autowired
    private DataReader dataReader;

    @Autowired
    private InitService initService;

    public void readProgram(String sessionId, byte[] data) {
        FutureUtils.getResultOrNull(readProgramAsync(sessionId, data));
    }

    @Async("ioTaskExecutor")
    public Future<Void> readProgramAsync(String sessionId, byte[] data) {
        initService.initThreadLocally(sessionId);
        dataReader.readProgram(data);
        return new AsyncResult<>(null);
    }

}
