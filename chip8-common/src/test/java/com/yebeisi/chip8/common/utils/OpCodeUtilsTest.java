package com.yebeisi.chip8.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OpCodeUtilsTest {

    @Test
    public void testGetDigits() {
        List<byte[]> origins = Arrays.asList(
            new byte[] {0x1F, 0x2A},
            new byte[] {0x0F, (byte) 0xA0},
            new byte[] {(byte) 0xFF, (byte) 0xFA}
        );

        List<byte[]> expecteds = Arrays.asList(
            new byte[] {0x1, 0xF, 0x2, 0xA},
            new byte[] {0x0, 0xF, (byte) 0xA, 0x0},
            new byte[] {(byte) 0xF, 0xF, (byte) 0xF, 0xA}
        );

        List<byte[]> actuals = origins.stream()
                .map(OpCodeUtils::getDigits)
                .collect(Collectors.toList());

        for(int i = 0; i < origins.size(); ++i) {
            Assertions.assertArrayEquals(expecteds.get(i), actuals.get(i));
        }
    }
}
