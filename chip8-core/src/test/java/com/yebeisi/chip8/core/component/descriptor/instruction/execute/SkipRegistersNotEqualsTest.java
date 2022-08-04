package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.SkipRegistersNotEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkipRegistersNotEqualsTest extends InstructionBaseTest{

    @Autowired
    private SkipRegistersNotEquals instruction;

    @Test
    public void testExecute() {
        for(int i = 0; i <= 0xF; ++i) {
            for(int j = 0; j <= 0xF; ++j) {
                if(i == j) {
                    continue;
                }

                byte b1 = (byte) (0x50 | i);
                byte b2 = (byte) (j << 4);
                byte[] code = new byte[] {b1, b2};

                short initPc = 0x2333;
                short expectedPc = 0x2333;
                registerController.setPc(initPc);

                registerController.setRegisterValue(i, (byte) 0x55);
                registerController.setRegisterValue(j, (byte) 0x55);

                instruction.decode(code);
                instruction.execute();

                short actualPc = registerController.getPc();

                Assertions.assertEquals(expectedPc, actualPc);
            }
        }

        for(int i = 0; i < 0xF; ++i) {
            for(int j = 0; j < 0xF; ++j) {
                if(i == j) {
                    continue;
                }

                byte b1 = (byte) (0x50 | i);
                byte b2 = (byte) (j << 4);
                byte[] code = new byte[] {b1, b2};

                short initPc = 0x2333;
                short expectedPc = 0x2335;
                registerController.setPc(initPc);

                registerController.setRegisterValue(i, (byte) 0x55);
                registerController.setRegisterValue(j, (byte) 0x51);

                instruction.decode(code);
                instruction.execute();

                short actualPc = registerController.getPc();

                Assertions.assertEquals(expectedPc, actualPc);
            }
        }

    }

}
