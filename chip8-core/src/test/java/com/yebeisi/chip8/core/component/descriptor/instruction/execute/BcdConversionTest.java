package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.BcdConversion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BcdConversionTest extends InstructionBaseTest {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private BcdConversion instruction;

    @Test
    public void testExecute() {
        byte[] bytes = new byte[] {
            0x11, (byte) 0xff, (byte) 0xee, (byte) 0x00
        };

        List<List<Byte>> expecteds = Arrays.asList(
                Arrays.asList((byte) 0, (byte) 1, (byte) 7),
                Arrays.asList((byte) 2, (byte) 5, (byte) 5),
                Arrays.asList((byte) 2, (byte) 3, (byte) 8),
                Arrays.asList((byte) 0, (byte) 0, (byte) 0)
        );

        for(int i = 0; i <= 0xF; ++i) {
            for(int j = 0; j < bytes.length; ++j) {
                byte b1 = (byte) (0xF0 | i);
                byte b2 = (byte) 0x33;
                short I = (short) secureRandom.nextInt();
                while(I < 9  || I > 0xFF0) {
                    I = (short) secureRandom.nextInt();
                }

                registerController.setI(I);
                registerController.setRegisterValue(i, bytes[j]);

                byte[] code = new byte[] {b1, b2};

                instruction.decode(code);
                instruction.execute();

                for(int k = 0; k < 3; ++k) {
                    byte actual = memoryController.get((short) (I + k));
                    byte expected = expecteds.get(j).get(k);

                    Assertions.assertEquals(expected, actual);
                }
            }
        }
    }
}
