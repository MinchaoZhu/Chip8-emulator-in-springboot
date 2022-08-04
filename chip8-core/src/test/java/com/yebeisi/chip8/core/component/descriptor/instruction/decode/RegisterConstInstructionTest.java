package com.yebeisi.chip8.core.component.descriptor.instruction.decode;

import com.yebeisi.chip8.core.component.descriptor.instruction.execute.InstructionBaseTest;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterConstInstruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RegisterConstInstructionTest extends InstructionBaseTest {

    @Autowired
    private List<RegisterConstInstruction> instructions;

    @Test
    public void testDecode() {
        instructions.forEach(instruction -> {
            byte[] immediates = new byte[] {
                    (byte) 0xFF, 0x00, 0x01, (byte) 0xFE, (byte) 0xF0
            };

            for(int i = 0; i < 0xF; ++i) {
                for(int j = 0; j < immediates.length; ++j) {
                    byte immediate = immediates[j];

                    byte b1 = (byte) (0x30 | i);
                    byte b2 = immediate;

                    byte[] equalsCode = new byte[] {b1, b2};

                    instruction.decode(equalsCode);

                    byte vxIndex = getVxIndex(instruction);
                    Assertions.assertEquals(i, vxIndex);
                    byte nn = getNN(instruction);
                    Assertions.assertEquals(immediate, nn);
                }
            }
        });
    }

}
