package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;

@Descriptor
public class BcdConversion extends RegisterInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xF == codeDigits[0] && 0x3 == codeDigits[2] && 0x3 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        int vxInInt = 0xFF & getVx();

        byte[] decimals = convertByteToDecimal(vxInInt);
        short address = registerController.getI();

        memoryController.set(decimals[0], address);
        memoryController.set(decimals[1], (short) (address + 1));
        memoryController.set(decimals[2], (short) (address + 2));
    }

    private byte[] convertByteToDecimal(int b) {
        byte[] result = new byte[3];
        byte value;

        value = (byte) (b % 10);
        b = (byte) (b / 10);
        result[2] = value;

        value = (byte) (b % 10);
        b = (byte) (b / 10);
        result[1] = value;

        value = (byte) (b % 10);
        result[0] = value;

        return result;
    }

}
