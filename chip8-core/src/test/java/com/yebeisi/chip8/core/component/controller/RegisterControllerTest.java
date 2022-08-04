package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.core.CoreBaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class RegisterControllerTest extends CoreBaseTest {

    @Autowired
    RegisterController registerController;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Test
    public void testPc() {
        short pc;
        pc = registerController.getPc();

        Assertions.assertEquals(0x200, pc);

        short newValue = 1258;
        registerController.setPc(newValue);
        pc = registerController.getPc();

        Assertions.assertEquals(newValue, pc);
    }

    @Test
    public void testI() {
        short I;
        I = registerController.getI();

        Assertions.assertEquals(0, I);

        short newValue = 1258;
        registerController.setI(newValue);
        I = registerController.getI();

        Assertions.assertEquals(newValue, I);
    }

    @Test
    public void testDataRegister() {
        List<Integer> validIndices = IntStream.rangeClosed(0, 15)
                .boxed().collect(Collectors.toList());
        List<Integer> invalidIndices = Arrays.asList(-1, 16, 10086);
        byte[] bytes = new byte[validIndices.size()];
        getRandBytes(bytes);
        validIndices.forEach(index -> {
            byte value;
            value = registerController.getRegisterValue(index);

            Assertions.assertEquals(0, value);

            byte newValue = bytes[index];
            registerController.setRegisterValue(index, newValue);
            value = registerController.getRegisterValue(index);

            Assertions.assertEquals(newValue, value);
        });

        invalidIndices.forEach(index -> {
            try {
                registerController.getRegisterValue(index);
            } catch (RuntimeException ignored) {}
        });
    }

    private void getRandBytes(byte[] bytes) {
        secureRandom.nextBytes(bytes);
    }

}
