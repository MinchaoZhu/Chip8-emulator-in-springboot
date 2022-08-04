package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;

@Descriptor
public class RegistersAnd extends RegistersInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x8 == codeDigits[0] && 0x2 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        setVx((byte) (getVx() & getVy()));
    }
}
