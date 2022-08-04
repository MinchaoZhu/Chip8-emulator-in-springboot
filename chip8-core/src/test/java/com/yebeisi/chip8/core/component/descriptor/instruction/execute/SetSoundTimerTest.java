package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.SetSoundTimer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;

@SpringBootTest
public class SetSoundTimerTest extends InstructionBaseTest {

    @Autowired
    private SetSoundTimer instruction;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Test
    public void testExecute() {
        for(int i = 0; i <= 0xF; ++i) {
            byte[] randPositiveByte = new byte[1];
            secureRandom.nextBytes(randPositiveByte);
            while(randPositiveByte[0] < 0x0) {
                secureRandom.nextBytes(randPositiveByte);
            }

            byte b1 = (byte) (0xF0 | i);
            byte b2 = (byte) 0x18;

            registerController.setRegisterValue(i, randPositiveByte[0]);
            byte[] code = new byte[] {b1, b2};
            timerController.setSoundTimer((byte) 0);
            instruction.decode(code);
            instruction.execute();

            byte expected = randPositiveByte[0];
            byte actual = timerController.getSoundTimer();

            Assertions.assertEquals(expected, actual);
        }

    }

}
