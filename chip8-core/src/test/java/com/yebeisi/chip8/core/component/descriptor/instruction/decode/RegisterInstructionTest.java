package com.yebeisi.chip8.core.component.descriptor.instruction.decode;

import com.yebeisi.chip8.core.component.descriptor.instruction.execute.InstructionBaseTest;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RegisterInstructionTest extends InstructionBaseTest {

    @Autowired
    private List<RegisterInstruction> instructions;

    @Test
    public void testDecode() {
        instructions.forEach(instruction -> {
            for(int i = 0; i < 0xF; ++i) {
                byte b1 = (byte) (0xE0 | i);
                byte b2 = 0x01;

                byte[] code = new byte[] {b1, b2};

                instruction.decode(code);

                byte vxIndex = getVxIndex(instruction);
                Assertions.assertEquals(i, vxIndex);
            }
        });
    }

}
