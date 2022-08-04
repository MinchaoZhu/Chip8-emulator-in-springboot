package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.ConstInstruction;

@Descriptor
public class Jump extends ConstInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x1 == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        registerController.setPc(getNNN());
    }

}
