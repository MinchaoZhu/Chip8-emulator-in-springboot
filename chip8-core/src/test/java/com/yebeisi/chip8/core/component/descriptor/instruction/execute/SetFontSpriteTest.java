package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.common.config.FontSetConfig;
import com.yebeisi.chip8.core.component.descriptor.instruction.SetFontSprite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;

@SpringBootTest
public class SetFontSpriteTest extends InstructionBaseTest {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private SetFontSprite instruction;

    @Test
    public void testExecute() {
        for(int i = 0; i <= 0xF; ++i) {
            byte b1 = (byte) (0xF0 | i);
            byte b2 = (byte) 0x29;
            short I = (short) secureRandom.nextInt();

            byte b = (byte) secureRandom.nextInt();
            while(b < 0x0 || b > 0xF) {
                b = (byte) secureRandom.nextInt();
            }

            registerController.setRegisterValue(i, b);
            byte[] code = new byte[] {b1, b2};

            instruction.decode(code);
            instruction.execute();

            short expected = (short) (FontSetConfig.getFontSetMemAddress() + b);
            short actual = registerController.getI();

            Assertions.assertEquals(expected, actual);
        }
    }

}
