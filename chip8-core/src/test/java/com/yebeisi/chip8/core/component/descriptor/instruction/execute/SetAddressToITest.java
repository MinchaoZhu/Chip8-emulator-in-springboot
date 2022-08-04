package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.SetAddressToI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SetAddressToITest extends InstructionBaseTest {

    @Autowired
    private SetAddressToI instruction;

    @Test
    public void testExecute() {
        List<byte[]> origins = Arrays.asList(
                new byte[] {(byte) 0xA1, (byte) 0x5F},
                new byte[] {(byte) 0xAF, (byte) 0xFF},
                new byte[] {(byte) 0xA0, (byte) 0x00}
        );

        List<Short> expecteds = Arrays.asList(
                (short) 0x15F,
                (short) 0xFFF,
                (short) 0x000
        );
        for(int i = 0; i < origins.size(); ++i) {
            instruction.decode(origins.get(i));
            instruction.execute();
            Short I = registerController.getI();
            Assertions.assertEquals(expecteds.get(i), I);
        }
    }

}
