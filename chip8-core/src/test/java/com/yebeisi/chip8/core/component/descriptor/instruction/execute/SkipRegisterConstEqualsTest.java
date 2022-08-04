package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.SkipRegisterConstEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkipRegisterConstEqualsTest extends InstructionBaseTest{

    @Autowired
    private SkipRegisterConstEquals instruction;

    @Test
    public void testExecute() {
        byte[] immediates = new byte[] {
                (byte) 0x00, (byte) 0xFF, (byte) 0xF1, (byte) 0xF2, (byte) 0x0F
        };

        for(int i = 0; i <= 0xF; ++i) {
            for(int j = 0; j < immediates.length; ++j) {
                byte immediate = immediates[j];

                byte b1 = (byte) (0x30 | i);
                byte b2 = immediate;

                byte[] equalsCode = new byte[] {b1, b2};

                registerController.setRegisterValue(i, immediate);

                short initPc = 0x2333;
                short expectedPc = 0x2335;
                registerController.setPc(initPc);

                instruction.decode(equalsCode);
                instruction.execute();

                short actualPc = registerController.getPc();

                Assertions.assertEquals(expectedPc, actualPc);
            }
        }

        byte[] notEqualImmediates = new byte[] {
                (byte) 0xFF, 0x00, 0x01, (byte) 0xFE, (byte) 0xF0
        };

        for(int i = 0; i < 0xF; ++i) {
            for(int j = 0; j < notEqualImmediates.length; ++j) {
                byte notEqualImmediate = notEqualImmediates[j];
                byte immediate = immediates[j];

                byte b1 = (byte) (0x30 | i);
                byte b2 = notEqualImmediate;

                byte[] equalsCode = new byte[] {b1, b2};

                registerController.setRegisterValue(i, immediate);

                short initPc = 0x2333;
                short expectedPc = 0x2333;
                registerController.setPc(initPc);

                instruction.decode(equalsCode);
                instruction.execute();

                short actualPc = registerController.getPc();

                Assertions.assertEquals(expectedPc, actualPc);
            }
        }



    }

}
