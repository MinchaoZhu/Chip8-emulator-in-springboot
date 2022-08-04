package com.yebeisi.chip8.common.config;

import java.util.HashMap;
import java.util.Map;

public final class FontSetConfig {

    private FontSetConfig() {}

    private static final short FONT_SET_MEM_ADDRESS = 0x50;

    private static final byte[] FONT_SET = new byte[] {
            (byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0, // 0
            (byte) 0x20, (byte) 0x60, (byte) 0x20, (byte) 0x20, (byte) 0x70, // 1
            (byte) 0xF0, (byte) 0x10, (byte) 0xF0, (byte) 0x80, (byte) 0xF0, // 2
            (byte) 0xF0, (byte) 0x10, (byte) 0xF0, (byte) 0x10, (byte) 0xF0, // 3
            (byte) 0x90, (byte) 0x90, (byte) 0xF0, (byte) 0x10, (byte) 0x10, // 4
            (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x10, (byte) 0xF0, // 5
            (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x90, (byte) 0xF0, // 6
            (byte) 0xF0, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x40, // 7
            (byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x90, (byte) 0xF0, // 8
            (byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x10, (byte) 0xF0, // 9
            (byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x90, (byte) 0x90, // A
            (byte) 0xE0, (byte) 0x90, (byte) 0xE0, (byte) 0x90, (byte) 0xE0, // B
            (byte) 0xF0, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xF0, // C
            (byte) 0xE0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xE0, // D
            (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x80, (byte) 0xF0, // E
            (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x80, (byte) 0x80, // F
    };

    private static final Map<String, Integer> FONT_INDEX = new HashMap<>();

    static {
        FONT_INDEX.put("0", 0x0);
        FONT_INDEX.put("1", 0x1);
        FONT_INDEX.put("2", 0x2);
        FONT_INDEX.put("3", 0x3);
        FONT_INDEX.put("4", 0x4);
        FONT_INDEX.put("5", 0x5);
        FONT_INDEX.put("6", 0x6);
        FONT_INDEX.put("7", 0x7);
        FONT_INDEX.put("8", 0x8);
        FONT_INDEX.put("9", 0x9);
        FONT_INDEX.put("A", 0xA);
        FONT_INDEX.put("B", 0xB);
        FONT_INDEX.put("C", 0xC);
        FONT_INDEX.put("D", 0xD);
        FONT_INDEX.put("E", 0xE);
        FONT_INDEX.put("F", 0xF);
    }

    public static byte[] getFontSet() {
        return FONT_SET;
    }

    public static short getFontMemAddress(String ch) {
        return (short) (FONT_SET_MEM_ADDRESS + FONT_INDEX.getOrDefault(ch, 0));
    }

    public static short getFontSetMemAddress() {
        return FONT_SET_MEM_ADDRESS;
    }

}
