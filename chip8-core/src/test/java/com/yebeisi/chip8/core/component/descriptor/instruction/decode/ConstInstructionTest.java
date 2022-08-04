package com.yebeisi.chip8.core.component.descriptor.instruction.decode;

import com.yebeisi.chip8.core.component.descriptor.instruction.execute.InstructionBaseTest;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.ConstInstruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ConstInstructionTest extends InstructionBaseTest {

    @Autowired
    private List<ConstInstruction> instructions;

    @Test
    public void testDecode() {
        instructions.forEach(instruction -> {
            List<byte[]> origins = Arrays.asList(
                    new byte[] {0x11, (byte) 0x5F},
                    new byte[] {0x1F, (byte) 0xFF},
                    new byte[] {0x10, (byte) 0x00}
            );

            List<Short> expecteds = Arrays.asList(
                    (short) 0x15F,
                    (short) 0xFFF,
                    (short) 0x000
            );

            for(int i = 0; i < origins.size(); ++i) {
                instruction.decode(origins.get(i));
                short actual = getNNN(instruction);
                Assertions.assertEquals(expecteds.get(i), actual);
            }
        });
    }

}
