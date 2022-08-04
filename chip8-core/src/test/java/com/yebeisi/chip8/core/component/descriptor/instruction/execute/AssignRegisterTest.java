package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.AssignRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssignRegisterTest extends InstructionBaseTest {

    @Autowired
    private AssignRegister instruction;

    @Test
    public void testExecute() {
        for(int i = 0; i <= 0xF; ++i) {
            for(int j = 0; j <= 0xF; ++j) {
                if(i == j) {
                    continue;
                }

                byte b1 = (byte) (0x80 | i);
                byte b2 = (byte) (j << 4);
                byte[] code = new byte[] {b1, b2};

                byte v2 = (byte) (0xF1 | j);

                registerController.setRegisterValue(j, v2);

                instruction.decode(code);
                instruction.execute();

                byte actual  = registerController.getRegisterValue(i);
                Assertions.assertEquals(v2, actual);
            }
        }
    }

}
