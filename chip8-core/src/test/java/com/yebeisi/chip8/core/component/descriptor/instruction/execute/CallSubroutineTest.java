package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.CallSubroutine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CallSubroutineTest extends InstructionBaseTest {

    @Autowired
    private CallSubroutine instruction;

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
            Short currentPc = registerController.getPc();
            instruction.decode(origins.get(i));
            instruction.execute();
            Short stackPushed = stackController.peek();
            Short pc = registerController.getPc();
            Assertions.assertEquals(expecteds.get(i), pc);
            Assertions.assertEquals(stackPushed, currentPc);
        }
    }
}
