package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 8XY0
 */
@Descriptor
public class AssignRegister extends RegistersInstruction {

    @Autowired
    RegisterController registerController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] digits = OpCodeUtils.getDigits(code);
        return 0x8 == digits[0] && 0x0 == digits[3] ;
    }

    @Override
    protected void executeInternally() {
        setVx(getVy());
    }

}
