package com.yebeisi.chip8.client;

import com.yebeisi.chip8.client.async_proxy.DataReaderProxy;
import com.yebeisi.chip8.client.async_proxy.GraphIOProxy;
import com.yebeisi.chip8.client.async_proxy.KeyboardIOProxy;
import com.yebeisi.chip8.common.data.Session;
import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.common.utils.BeanUtils;
import com.yebeisi.chip8.core.Executor;
import com.yebeisi.chip8.core.service.InitService;
import com.yebeisi.chip8.core.service.session.SessionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Slf4j
@Data
public class Chip8 {

    private String sessionId;

    private String user;

    private Session session;

    private Map<String, Keyboard.KeyEnum> keyCodeMap = new HashMap<>();

    public Chip8(String user) {
        this.user = user;
        this.sessionId = createNewSession(user);
        loadDefaultKeyCodeMap();
    }

    private void loadDefaultKeyCodeMap() {
        keyCodeMap.put("1", Keyboard.KeyEnum.Key_1);
        keyCodeMap.put("2", Keyboard.KeyEnum.Key_2);
        keyCodeMap.put("3", Keyboard.KeyEnum.Key_3);
        keyCodeMap.put("4", Keyboard.KeyEnum.Key_C);

        keyCodeMap.put("q", Keyboard.KeyEnum.Key_4);
        keyCodeMap.put("w", Keyboard.KeyEnum.Key_5);
        keyCodeMap.put("e", Keyboard.KeyEnum.Key_6);
        keyCodeMap.put("r", Keyboard.KeyEnum.Key_D);

        keyCodeMap.put("a", Keyboard.KeyEnum.Key_7);
        keyCodeMap.put("s", Keyboard.KeyEnum.Key_8);
        keyCodeMap.put("d", Keyboard.KeyEnum.Key_9);
        keyCodeMap.put("f", Keyboard.KeyEnum.Key_E);

        keyCodeMap.put("z", Keyboard.KeyEnum.Key_A);
        keyCodeMap.put("x", Keyboard.KeyEnum.Key_0);
        keyCodeMap.put("c", Keyboard.KeyEnum.Key_B);
        keyCodeMap.put("v", Keyboard.KeyEnum.Key_F);
    }

    private String createNewSession(String user) {
        InitService initService = BeanUtils.getBean(InitService.class);
        sessionId = initService.createNewSession(user);
        initContext();
        return sessionId;
    }

    private void initContext() {
        InitService initService = BeanUtils.getBean(InitService.class);
        SessionService sessionService = BeanUtils.getBean(SessionService.class);

        session = sessionService.getSession(sessionId);
        Future<Boolean> initResult = initService.initContext(sessionId);
        while(!initResult.isDone()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void readProgram(byte[] bytes) {
        DataReaderProxy dataReaderProxy = BeanUtils.getBean(DataReaderProxy.class);
        dataReaderProxy.readProgram(sessionId, bytes);
    }

    public String getStatus() {
        return session.getStatus();
    }

    public void setSpeed(double acceleration) {
        if(acceleration < 0.5 || acceleration > 2.0) {
            return;
        }

        session.setAcceleration(acceleration);
    }

    public void run() {
        new Thread(this::runInternally).start();
    }

    public void pause() {
        session.setStatus(Session.PAUSE);
    }

    public void resume() {
        if(Session.PAUSE.equals(session.getStatus())) {
            session.setStatus(Session.RUNNING);
        }
    }

    public void stop() {
        session.setStatus(Session.STOP);
    }

    public void reset() {
        session.setStatus(Session.PAUSE);
        initContext();
    }

    public void pressKey(String keyCode) {
        if(session.getStatus().equals(Session.RUNNING)) {
            KeyboardIOProxy keyboardIOProxy = BeanUtils.getBean(KeyboardIOProxy.class);
            Keyboard.KeyEnum key = keyCodeMap.get(keyCode);
            keyboardIOProxy.pressKey(sessionId, key);
        }
    }

    public void releaseKey(String keyCode) {
        if(session.getStatus().equals(Session.RUNNING)) {
            KeyboardIOProxy keyboardIOProxy = BeanUtils.getBean(KeyboardIOProxy.class);
            Keyboard.KeyEnum key = keyCodeMap.get(keyCode);
            keyboardIOProxy.releaseKey(sessionId, key);
        }
    }

    /**
     * read graph memory
     * @return graph memory or null by exception
     */
    public int[][] getGraph() {
        GraphIOProxy graphIOProxy = BeanUtils.getBean(GraphIOProxy.class);
        return graphIOProxy.getGraphMemory(sessionId);
    }

    public void removeSession() {
        InitService initService = BeanUtils.getBean(InitService.class);
        initService.removeSession(sessionId);
    }

    public void readFile(byte[] bytes) {
        if(session.getStatus().equals(Session.NEW)) {
            DataReaderProxy dataReaderProxy = BeanUtils.getBean(DataReaderProxy.class);
            dataReaderProxy.readProgram(sessionId, bytes);
            session.setStatus(Session.READY);
        }
    }

    private void runInternally() {
        if(!Session.READY.equals(session.getStatus())) {
            throw new RuntimeException("session is not ready or is already running: " + session.getStatus());
        }

        if(StringUtils.isBlank(sessionId)) {
            throw new RuntimeException("no active session");
        }
        session.setStatus(Session.RUNNING);

        Executor executor = BeanUtils.getBean(Executor.class);
        SessionService sessionService = BeanUtils.getBean(SessionService.class);
        while(true) {
            if(!sessionService.sessionExists(sessionId)) {
                break;
            }

            if(session.isErrored()) {
                session.setStatus(Session.STOP);
                throw new RuntimeException("session status is errored", session.getError());
            }

            if(Session.PAUSE.equals(session.getStatus())) {
                continue;
            }
            if(Session.STOP.equals(session.getStatus())) {
                break;
            }

            timeCounter();
            executor.execute(sessionId);
        }
    }

    private void timeCounter(){
        if(StringUtils.isBlank(sessionId)) {
            throw new RuntimeException("no active session");
        }
        double delay = 1000 / (Session.BASE_FREQUENCY * session.getAcceleration());
        try {
            Thread.sleep((long) delay);
        } catch (InterruptedException e) {
            throw new RuntimeException("timer counter sleep error: " + e.getMessage());
        }
    }

}
