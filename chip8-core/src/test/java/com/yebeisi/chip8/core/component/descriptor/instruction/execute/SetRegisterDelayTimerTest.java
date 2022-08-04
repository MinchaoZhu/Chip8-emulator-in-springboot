package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.SetRegisterDelayTimer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;

@SpringBootTest
public class SetRegisterDelayTimerTest extends InstructionBaseTest {

    @Autowired
    private SetRegisterDelayTimer instruction;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Test
    public void testExecute() {

        for(int i = 0; i <= 0xF; ++i) {
            byte[] randPositiveByte = new byte[1];
            secureRandom.nextBytes(randPositiveByte);
            while(randPositiveByte[0] < 1) {
                secureRandom.nextBytes(randPositiveByte);
            }

            timerController.setDelayTimer(randPositiveByte[0]);

            byte b1 = (byte) (0xF0 | i);
            byte b2 = 0x07;

            byte[] code = new byte[] {b1, b2};

            instruction.decode(code);
            instruction.execute();


            byte expected = randPositiveByte[0];
            byte actual = registerController.getRegisterValue(i);

            Assertions.assertEquals(expected, actual);
        }
    }
}
