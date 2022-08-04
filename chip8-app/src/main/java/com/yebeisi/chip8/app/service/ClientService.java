package com.yebeisi.chip8.app.service;

import com.yebeisi.chip8.app.base.CommandCode;
import com.yebeisi.chip8.app.model.Chip8Info;
import com.yebeisi.chip8.client.Chip8;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientService {

    private static final Map<String, Chip8> clientMap = new ConcurrentHashMap<>();

    private static final Map<String, Long> clientActiveTime = new ConcurrentHashMap<>();

    private static final long CLIENT_EXPIRATION_TIME = 2 * 60 * 1000; // 2 min

    public String newClient(String user) {
        Chip8 client = new Chip8(user);
        clientMap.put(client.getSessionId(), client);
        clientActiveTime.put(client.getSessionId(), System.currentTimeMillis());
        return client.getSessionId();
    }

    public Chip8 getClient(String sessionId) {
        clientActiveTime.put(sessionId, System.currentTimeMillis());
        return clientMap.get(sessionId);
    }

    public Chip8Info getClientInfo(String sessionId) {
        clientActiveTime.put(sessionId, System.currentTimeMillis());
        Chip8 client = clientMap.get(sessionId);
        return Chip8Info.builder()
                .sessionId(sessionId)
                .user(client.getUser())
                .status(client.getStatus())
                .acceleration(client.getSession().getAcceleration())
                .build();
    }

    public int[][] getGraph(String sessionId) {
        return getClient(sessionId).getGraph();
    }

    public void pressKey(String sessionId, String keyCode) {
        Chip8 client = getClient(sessionId);
        client.pressKey(keyCode);
    }

    public void releaseKey(String sessionId, String keyCode) {
        Chip8 client = getClient(sessionId);
        client.releaseKey(keyCode);
    }

    public void doCommand(String sessionId, String commandCode) {
        Chip8 client = getClient(sessionId);

        if(CommandCode.PAUSE.getCode().equals(commandCode)) {
            client.pause();
        } else if (CommandCode.STOP.getCode().equals(commandCode)) {
            client.stop();
            removeClient(sessionId);
        } else if (CommandCode.RUN.getCode().equals(commandCode)) {
            client.run();
        } else if (CommandCode.RESUME.getCode().equals(commandCode)) {
            client.resume();
        } else if (CommandCode.INIT.getCode().equals(commandCode)) {
            client.reset();
        }

    }

    public void readGameFile(String sessionId, byte[] bytes) {
        Chip8 client = getClient(sessionId);
        client.readFile(bytes);
    }

    public void setAcceleration(String sessionId, Double acceleration) {
        Chip8 client = getClient(sessionId);
        client.setSpeed(acceleration);
    }

    @Scheduled(fixedRate = CLIENT_EXPIRATION_TIME)
    public void clearInActiveClient() {
        long current = System.currentTimeMillis();
        clientActiveTime.forEach((sessionId, lastActiveTime) -> {
            if(current - lastActiveTime > CLIENT_EXPIRATION_TIME) {
                removeClient(sessionId);
            }
        });

    }

    private void removeClient(String sessionId) {
        getClient(sessionId).removeSession();
        clientMap.remove(sessionId);
    }

}
