package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.RegisterShiftLeft;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistersShiftLeftTest extends InstructionBaseTest{

    @Autowired
    private RegisterShiftLeft instruction;

    @Test
    public void testExecute() {
        for(int x = 0; x < 0xF; ++x) {
            for(int y = 0; y < 0xF; ++y) {
                if(x == y) {
                    continue;
                }

                byte b1 = (byte) (0x80 | x);
                byte b2 = (byte) (y << 4);
                byte[] code = new byte[] {b1, b2};

                byte[] origins = new byte[] {0x00, 0x01, (byte) 0xFF, (byte) 0xFE, (byte) 0xF0};
                byte[] expected1 = new byte[] {0x00, 0x02, (byte) 0xFE, (byte) 0xFC, (byte) 0xE0};
                byte[] expected2 = new byte[] {0x0, 0x0, 0x1, 0x1, 0x1};

                for(int k = 0; k < origins.length; ++k) {
                    registerController.setRegisterValue(y, origins[k]);
                    instruction.decode(code);
                    instruction.execute();
                    byte actual1  = registerController.getRegisterValue(x);
                    byte actual2 = registerController.getRegisterValue(0xF);
                    Assertions.assertEquals(expected1[k], actual1);
                    Assertions.assertEquals(expected2[k], actual2);

                }
            }
        }
    }

}
