package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.core.component.descriptor.instruction.SkipIfPressed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SkipIfPressedTest extends InstructionBaseTest {

    @Autowired
    private SkipIfPressed instruction;

    @Test
    public void testExecute() {
        for(int i = 0; i <= 0xF; ++i) {
            for(byte j = 0; j <= 0xF; ++j) {
                byte b1 = (byte) (0xE0 | i);
                byte b2 = (byte) 0x9E;

                byte[] equalsCode = new byte[] {b1, b2};

                registerController.setRegisterValue(i, j);

                short initPc = 0x2333;
                short expectedPc = 0x2335;
                registerController.setPc(initPc);
                keyboardController.press(Keyboard.KeyEnum.getKeyEnumFromCode(j));

                instruction.decode(equalsCode);
                instruction.execute();

                short actualPc = registerController.getPc();

                Assertions.assertEquals(expectedPc, actualPc);
            }
        }

        for(int i = 0; i < 0xF; ++i) {
            for(byte j = 0; j < 0xF; ++j) {
                byte b1 = (byte) (0xE0 | i);
                byte b2 = (byte) 0x9E;

                byte[] equalsCode = new byte[] {b1, b2};

                registerController.setRegisterValue(i, j);

                short initPc = 0x2333;
                short expectedPc = 0x2333;
                registerController.setPc(initPc);
                keyboardController.release(Keyboard.KeyEnum.getKeyEnumFromCode(j));

                instruction.decode(equalsCode);
                instruction.execute();

                short actualPc = registerController.getPc();

                Assertions.assertEquals(expectedPc, actualPc);
            }
        }

    }

}
