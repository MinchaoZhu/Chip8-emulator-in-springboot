package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.RegistersDump;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;

@SpringBootTest
public class RegistersDumpTest extends InstructionBaseTest {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private RegistersDump instruction;

    @Test
    public void testExecute() {
        byte[] randBytes = new byte[0xF + 1];

        for(int i = 0; i <= 0xF; ++i) {
            secureRandom.nextBytes(randBytes);

            short I = (short) secureRandom.nextInt();
            while(I < 0x10  || I > 0xF00) {
                I = (short) secureRandom.nextInt();
            }
            registerController.setI(I);

            byte b1 = (byte) (0xF0 | i);
            byte b2 = (byte) 0x55;

            byte[] code = new byte[] {b1, b2};

            for(int j = 0; j <= i; ++j) {
                registerController.setRegisterValue(j, randBytes[j]);
            }

            instruction.decode(code);
            instruction.execute();

            for(int j = 0; j <= i; ++j) {
                byte expected = randBytes[j];
                byte actual = memoryController.get((short) (I + j));
                Assertions.assertEquals(expected, actual);
            }
        }
    }
}
