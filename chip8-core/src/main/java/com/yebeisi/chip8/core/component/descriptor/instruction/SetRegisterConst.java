package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterConstInstruction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 6XNN
 * Vx = NN
 * Sets VX to NN.
 */
@Descriptor
public class SetRegisterConst extends RegisterConstInstruction {

    @Autowired
    RegisterController registerController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x6 == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        setVx(getNN());
    }
}
