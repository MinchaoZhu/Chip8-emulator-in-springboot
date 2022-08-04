package com.yebeisi.chip8.core.component.descriptor.instruction.decode;

import com.yebeisi.chip8.core.component.descriptor.instruction.execute.InstructionBaseTest;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RegistersInstructionTest extends InstructionBaseTest {

    @Autowired
    private List<RegistersInstruction> instructions;

    @Test
    public void testDecode() {
        instructions.forEach(instruction -> {
            for(int i = 0; i < 0xF; ++i) {
                for(int j = 0; j < 0xF; ++j) {
                    if(i == j) {
                        continue;
                    }
                    byte n = RandomUtils.nextBytes(1)[0];
                    n = (byte) (n & 0x0F);
                    byte b1 = (byte) (i);
                    byte b2 = (byte) (j << 4);
                    b2 = (byte) (b2 | n);
                    byte[] code = new byte[] {b1, b2};

                    instruction.decode(code);

                    byte vxIndex = getVxIndex(instruction);
                    Assertions.assertEquals(i, vxIndex);
                    int vyIndex = getVyIndex(instruction);
                    Assertions.assertEquals(j, vyIndex);
                    int actualN = getN(instruction);
                    Assertions.assertEquals(n, actualN);
                }
            }
        });
    }



}
