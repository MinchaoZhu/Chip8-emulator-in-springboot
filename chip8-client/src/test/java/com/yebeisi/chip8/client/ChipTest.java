package com.yebeisi.chip8.client;

import com.yebeisi.chip8.common.data.Session;
import com.yebeisi.chip8.core.Executor;
import com.yebeisi.chip8.core.component.controller.GraphController;
import com.yebeisi.chip8.core.component.controller.MemoryController;
import com.yebeisi.chip8.core.service.session.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

@Slf4j
@SpringBootTest
public class ChipTest {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MemoryController memoryController;

    @Autowired
    private GraphController graphController;

    @MockBean
    private Executor executor;

    public ConcurrentMap<String, Integer> sessionExecutionCounter = new ConcurrentHashMap<>();

    private void mockExecute() {
        doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            String sessionId = (String) args[0];
            sessionExecutionCounter.putIfAbsent(sessionId, 0);
            sessionExecutionCounter.compute(sessionId, (k, v) -> 1 + v);
//            log.info("[mock execution] session: " + sessionId + ", thread: " + Thread.currentThread().getId() + ", counter: " + sessionExecutionCounter.get(sessionId));
            return null;
        }).when(executor).execute(anyString());
    }

    @Test
    public void testInitNewSession() {

        String user = "testuser";
        String status = Session.READY;

        Chip8 chip8 = new Chip8(user);
        String actualUser = chip8.getUser();
        String actualSessionId = chip8.getSessionId();
        String actualStatus = chip8.getStatus();

        sessionService.initSessionThreadLocally(actualSessionId);
        String sessionId = sessionService.getSessionThreadLocally().getSessionId();


        Assertions.assertEquals(user, actualUser);
        Assertions.assertEquals(status, actualStatus);
        Assertions.assertEquals(sessionId, actualSessionId);

    }

    @Test
    public void testReadProgram() {
        byte[] data = new byte[2000];

        try {
            SecureRandom.getInstanceStrong().nextBytes(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String user = "testuser";
        Chip8 chip8Client = new Chip8(user);
        chip8Client.readProgram(data);

        byte[] actuals = memoryController.getBytes((short) 0x200, 2000);

        Assertions.assertArrayEquals(data, actuals);
    }

    @Test
    public void testClientRun() throws InterruptedException {
        mockExecute();

        String user = "testuser";
        Chip8 chip8Client = new Chip8(user);
        chip8Client.setSpeed(0.5);

        String user2 = "testuser2";
        Chip8 chip8Client2 = new Chip8(user2);

        chip8Client.run();
        chip8Client2.run();
        Thread.sleep(2000);

        Integer counter1 = sessionExecutionCounter.get(chip8Client.getSessionId());
        Integer counter2 = sessionExecutionCounter.get(chip8Client2.getSessionId());

        Double actualRatio = 1.0 * counter1 / counter2;

        Assertions.assertTrue(actualRatio > 0.45 && actualRatio < 0.55);
    }

    @Test
    public void testReadGraph() throws NoSuchAlgorithmException {
        String user = "testuser";
        Chip8 chip8Client = new Chip8(user);

        int[][] graph0 = chip8Client.getGraph();

        int[][] expected = new int[graph0.length][graph0[0].length];

        Assertions.assertTrue(Arrays.deepEquals(expected, graph0));


        int[][] graph1 = new int[graph0.length][graph0[0].length];

        for(int i = 0; i < graph0.length; ++i) {
            for(int j = 0; j < graph0[0].length; ++j) {
                int v = SecureRandom.getInstanceStrong().nextInt();
                byte b = (byte) (((v % 2) + 2) % 2);
                graph1[i][j] = b;
                graphController.setGraphMemory(b, i, j);
            }
        }

        int[][] graph2 = chip8Client.getGraph();

        Assertions.assertTrue(Arrays.deepEquals(graph1, graph2));

    }

}
