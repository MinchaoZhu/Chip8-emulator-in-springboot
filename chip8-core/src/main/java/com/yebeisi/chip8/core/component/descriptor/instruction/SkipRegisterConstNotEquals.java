package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterConstInstruction;
import org.springframework.beans.factory.annotation.Autowired;


@Descriptor
public class SkipRegisterConstNotEquals extends RegisterConstInstruction {

    @Autowired
    RegisterController registerController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x4 == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        if(getNN() != getVx()) {
            short currentPc = registerController.getPc();
            registerController.setPc((short) (currentPc + 2));
        }
    }

}
