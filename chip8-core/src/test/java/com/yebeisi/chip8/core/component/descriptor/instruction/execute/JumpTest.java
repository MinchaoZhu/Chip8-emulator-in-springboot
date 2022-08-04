package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.Jump;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JumpTest extends InstructionBaseTest {

    @Autowired
    private Jump instruction;

    @Test
    public void testExecute() {
        List<byte[]> origins = Arrays.asList(
                new byte[] {0x21, (byte) 0x5F},
                new byte[] {0x2F, (byte) 0xFF},
                new byte[] {0x20, (byte) 0x00}
        );

        List<Short> expecteds = Arrays.asList(
                (short) 0x15F,
                (short) 0xFFF,
                (short) 0x000
        );
        for(int i = 0; i < origins.size(); ++i) {
            instruction.decode(origins.get(i));
            instruction.execute();
            Short pc = registerController.getPc();
            Assertions.assertEquals(expecteds.get(i), pc);
        }
    }



}
