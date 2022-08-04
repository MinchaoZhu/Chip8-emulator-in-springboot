package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;

@Descriptor
public class SetRegisterDelayTimer extends RegisterInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xF == codeDigits[0] && 0x0 == codeDigits[2] && 0x7 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        byte vxIndex = getVxIndex();
        registerController.setRegisterValue(vxIndex, timerController.getDelayTimer());
    }
}