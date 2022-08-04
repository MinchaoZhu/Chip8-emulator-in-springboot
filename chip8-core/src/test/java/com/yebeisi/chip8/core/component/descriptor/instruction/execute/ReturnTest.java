package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.Return;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReturnTest extends InstructionBaseTest {

    @Autowired
    private Return instruction;

    @Test
    public void testExecute() {
        short pushed = 0x222;
        stackController.push(pushed);

        instruction.execute();
        short actual = registerController.getPc();

        Assertions.assertEquals(pushed, actual);
    }

}
