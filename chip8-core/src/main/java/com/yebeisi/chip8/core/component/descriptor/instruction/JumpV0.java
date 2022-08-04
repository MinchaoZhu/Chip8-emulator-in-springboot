package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.ConstInstruction;

@Descriptor
public class JumpV0 extends ConstInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xB == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        registerController.setPc((short) (getNNN() + (0xFF & registerController.getRegisterValue(0x0))));
    }
}
