package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.AddIRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;

@SpringBootTest
public class AddIRegisterTest extends InstructionBaseTest {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private AddIRegister instruction;

    @Test
    public void testExecute() {
        for(int i = 0; i < 0xF; ++i) {
            byte b1 = (byte) (0xF0 | i);
            byte b2 = (byte) 0x1E;
            short I = (short) secureRandom.nextInt();
            byte b = (byte) secureRandom.nextInt();
            registerController.setI(I);
            registerController.setRegisterValue(i, b);

            byte[] code = new byte[] {b1, b2};

            instruction.decode(code);
            instruction.execute();

            short expected = (short) (I + b);
            short actual = registerController.getI();

            Assertions.assertEquals(expected, actual);
        }
    }

}
