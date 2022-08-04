package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;

@Descriptor
public class RegisterShiftLeft extends RegistersInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x8 == codeDigits[0] && 0xE == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        int vyInInt = 0xFF & getVy();
        int carry = (vyInInt & 0x80) >>> 7;
        setVx((byte) (vyInInt << 1));
        registerController.setRegisterValue(0xF, (byte) carry);
    }
}
