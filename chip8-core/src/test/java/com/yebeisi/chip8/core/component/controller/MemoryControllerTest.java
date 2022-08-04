package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.core.CoreBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SpringBootTest
public class MemoryControllerTest extends CoreBaseTest {

    @Autowired
    private MemoryController memoryController;

    @Test
    public void testget() {
        byte[] data = new byte[2000];

        try {
            SecureRandom.getInstanceStrong().nextBytes(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        memoryController.cp(data, (short) 0x200, 2000);

        byte[] actuals = memoryController.getBytes((short) 0x200, 2000);
        Assertions.assertArrayEquals(data, actuals);

        for(int i = 0; i < 2000; ++i) {
            Assertions.assertEquals(data[i], actuals[i]);
        }

    }


}
