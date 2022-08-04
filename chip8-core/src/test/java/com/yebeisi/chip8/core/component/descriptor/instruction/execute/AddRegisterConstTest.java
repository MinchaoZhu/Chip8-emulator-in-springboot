package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.AddRegisterConst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddRegisterConstTest extends InstructionBaseTest{

    @Autowired
    private AddRegisterConst instruction;

    @Test
    public void testExecute() {
        byte[] immediates = new byte[] {
                (byte) 0xFF, 0x00, 0x01, (byte) 0xFE, (byte) 0xF0
        };

        byte[] registerValues = new byte[] {
            (byte) 0x00, 0x23, (byte) 0xF1, (byte) 0x2F, (byte) 0x0A
        };

        byte[] addedValues = new byte[5];

        for(int i = 0 ; i < addedValues.length; ++i) {
            addedValues[i] = (byte) (immediates[i] + registerValues[i]);
        }

        for(int i = 0; i <= 0xF; ++i) {
            for(int j = 0; j < immediates.length; ++j) {
                registerController.setRegisterValue(i, registerValues[j]);
                byte immediate = immediates[j];

                byte b1 = (byte) (0x30 | i);
                byte b2 = immediate;

                byte[] equalsCode = new byte[] {b1, b2};

                instruction.decode(equalsCode);
                instruction.execute();

                byte actualValue = registerController.getRegisterValue(i);
                Assertions.assertEquals(addedValues[j], actualValue);
            }
        }

    }

}
