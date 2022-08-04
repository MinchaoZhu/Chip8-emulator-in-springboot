package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.core.component.descriptor.instruction.SetRegisterNextKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
public class SetRegisterNextKeyTest extends InstructionBaseTest {

    @Autowired
    private SetRegisterNextKey instruction;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Test
    public void test() throws ExecutionException, InterruptedException {
        for(int i = 0; i <= 0xF; ++i) {
            byte[] randPositiveByte = new byte[1];
            secureRandom.nextBytes(randPositiveByte);
            while(randPositiveByte[0] < 0x0 || randPositiveByte[0] > 0xF) {
                secureRandom.nextBytes(randPositiveByte);
            }

            byte b1 = (byte) (0xF0 | i);
            byte b2 = 0x0A;

            byte[] code = new byte[] {b1, b2};

            instruction.decode(code);

            Keyboard.KeyEnum keyEnum = Keyboard.KeyEnum.getKeyEnumFromCode(randPositiveByte[0]);


            String sessionId = sessionService.getSessionThreadLocally().getSessionId();
            Future<Void> future = CompletableFuture.runAsync(() -> {
                    initService.initThreadLocally(sessionId);
                    keyboardController.press(keyEnum);
            });
            instruction.execute();
            future.get();

            byte expected = keyEnum.getCode();
            byte actual = registerController.getRegisterValue(i);

            Assertions.assertEquals(expected, actual);
        }

    }

}
