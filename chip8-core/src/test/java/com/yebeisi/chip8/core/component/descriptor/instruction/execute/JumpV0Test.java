package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.JumpV0;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JumpV0Test extends InstructionBaseTest {

    @Autowired
    private JumpV0 instruction;

    @Test
    public void testExecute() {
        List<byte[]> origins1 = Arrays.asList(
                new byte[] {0x21, (byte) 0x5F},
                new byte[] {0x2E, (byte) 0xEE},
                new byte[] {0x20, (byte) 0x00}
        );

        List<Byte> origins2 = Arrays.asList(
                (byte) 0x1, (byte) 0x2, (byte) 0xFF
        );

        List<Short> expecteds = Arrays.asList(
                (short) 0x160,
                (short) 0xEF0,
                (short) 0x0FF
        );
        for(int i = 0; i < origins1.size(); ++i) {
            registerController.setRegisterValue(0x0, origins2.get(i));
            instruction.decode(origins1.get(i));
            instruction.execute();
            Short pc = registerController.getPc();
            Assertions.assertEquals(expecteds.get(i), pc);
        }

    }

}
