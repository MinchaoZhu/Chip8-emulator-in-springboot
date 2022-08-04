package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.ConstInstruction;

@Descriptor
public class SetAddressToI extends ConstInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xA == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        registerController.setI(getNNN());
    }
}
