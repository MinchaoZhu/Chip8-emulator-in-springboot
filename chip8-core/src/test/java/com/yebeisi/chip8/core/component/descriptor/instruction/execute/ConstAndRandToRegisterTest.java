package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.ConstAndRandToRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConstAndRandToRegisterTest extends InstructionBaseTest {

    @Autowired
    private ConstAndRandToRegister instruction;

    @Test
    public void testExecute() {
        byte b1 = (byte) (0xC0 | 1);
        byte b2 = 0x23;
        byte[] equalsCode = new byte[] {b1, b2};

        instruction.decode(equalsCode);
        instruction.execute();
    }

}
