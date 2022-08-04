package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 9XY0
 */
@Descriptor
public class SkipRegistersNotEquals extends RegistersInstruction {

    @Autowired
    RegisterController registerController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x9 == codeDigits[0] && 0x0 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        if(getVx() != getVy()) {
            short currentPc = registerController.getPc();
            registerController.setPc((short) (currentPc + 2));
        }
    }

}
