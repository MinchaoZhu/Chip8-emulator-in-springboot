package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.SetRegisterConst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SetRegisterConstTest extends InstructionBaseTest{
    @Autowired
    private SetRegisterConst instruction;

    @Test
    public void testExecute() {
        byte[] immediates = new byte[] {
                (byte) 0x00, (byte) 0xFF, (byte) 0xF1, (byte) 0xF2, (byte) 0x0F
        };
        for(int i = 0; i <= 0xF; ++i) {
            for(int j = 0; j < immediates.length; ++j) {
                byte immediate = immediates[j];

                byte b1 = (byte) (0x60 | i);
                byte b2 = immediate;

                byte[] equalsCode = new byte[] {b1, b2};

                instruction.decode(equalsCode);
                instruction.execute();

                byte actualValue = registerController.getRegisterValue(i);
                Assertions.assertEquals(immediate, actualValue);
            }
        }

    }

}
