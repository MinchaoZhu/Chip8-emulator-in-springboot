package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.config.FontSetConfig;
import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;

import java.util.HashMap;
import java.util.Map;

@Descriptor
public class SetFontSprite extends RegisterInstruction {

    private static final Map<Byte, String> BYTE_TO_CHAR = new HashMap<>();

    static {
        BYTE_TO_CHAR.put((byte) 0x0, "0");
        BYTE_TO_CHAR.put((byte) 0x1, "1");
        BYTE_TO_CHAR.put((byte) 0x2, "2");
        BYTE_TO_CHAR.put((byte) 0x3, "3");
        BYTE_TO_CHAR.put((byte) 0x4, "4");
        BYTE_TO_CHAR.put((byte) 0x5, "5");
        BYTE_TO_CHAR.put((byte) 0x6, "6");
        BYTE_TO_CHAR.put((byte) 0x7, "7");
        BYTE_TO_CHAR.put((byte) 0x8, "8");
        BYTE_TO_CHAR.put((byte) 0x9, "9");
        BYTE_TO_CHAR.put((byte) 0xA, "A");
        BYTE_TO_CHAR.put((byte) 0xB, "B");
        BYTE_TO_CHAR.put((byte) 0xC, "C");
        BYTE_TO_CHAR.put((byte) 0xD, "D");
        BYTE_TO_CHAR.put((byte) 0xE, "E");
        BYTE_TO_CHAR.put((byte) 0xF, "F");
    }

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xF == codeDigits[0] && 0x2 == codeDigits[2] && 0x9 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        byte vx = getVx();
        String ch = convertByteToChar(vx);
        short characterFontAddress = FontSetConfig.getFontMemAddress(ch);
        registerController.setI(characterFontAddress);
    }

    private String convertByteToChar(byte b) {
        return BYTE_TO_CHAR.getOrDefault(b, "0");
    }

}
