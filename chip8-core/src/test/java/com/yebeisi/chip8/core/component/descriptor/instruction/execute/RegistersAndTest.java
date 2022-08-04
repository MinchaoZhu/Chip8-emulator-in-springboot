package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.RegistersAnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistersAndTest extends InstructionBaseTest {

    @Autowired
    private RegistersAnd instruction;

    @Test
    public void testExecute() {
        for(int x = 0; x <= 0xF; ++x) {
            for(int y = 0; y <= 0xF; ++y) {
                if(x == y) {
                    continue;
                }

                byte b1 = (byte) (0x80 | x);
                byte b2 = (byte) (y << 4);
                byte[] code = new byte[] {b1, b2};

                byte[] origins1 = new byte[] {0x11, (byte) 0xFF, 0x00, 0x00, (byte) 0xF0};
                byte[] origins2 = new byte[] {0x10, 0x01, 0x10, (byte) 0xFF, 0x0F};
                byte[] expected = new byte[] {0x10, 0x01, 0x00, (byte) 0x00, (byte) 0x00};

                for(int k = 0; k < origins1.length; ++k) {
                    registerController.setRegisterValue(x, origins1[k]);
                    registerController.setRegisterValue(y, origins2[k]);
                    instruction.decode(code);
                    instruction.execute();
                    byte actual  = registerController.getRegisterValue(x);
                    Assertions.assertEquals(expected[k], actual);
                }
            }
        }
    }

}
