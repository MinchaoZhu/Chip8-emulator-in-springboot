package com.yebeisi.chip8.common.device;

import lombok.Getter;
import lombok.Setter;

public class Keyboard {

    @Getter
    private final boolean[] keyFlags = new boolean[16];

    @Getter
    @Setter
    private KeyEnum next = null;

    public enum KeyEnum {

        Key_0((byte) 0x0),
        Key_1((byte) 0x1),
        Key_2((byte) 0x2),
        Key_3((byte) 0x3),
        Key_4((byte) 0x4),
        Key_5((byte) 0x5),
        Key_6((byte) 0x6),
        Key_7((byte) 0x7),
        Key_8((byte) 0x8),
        Key_9((byte) 0x9),
        Key_A((byte) 0xA),
        Key_B((byte) 0xB),
        Key_C((byte) 0xC),
        Key_D((byte) 0xD),
        Key_E((byte) 0xE),
        Key_F((byte) 0xF),
        ;

        final private byte code;

        KeyEnum(byte code) {
            this.code = code;
        }

        public byte getCode() {
            return code;
        }

        public static KeyEnum getKeyEnumFromCode(byte code) {
            for(KeyEnum keyEnum : KeyEnum.values()) {
                if(keyEnum.getCode() == code) {
                    return keyEnum;
                }
            }
            return null;
        }
    }

}
