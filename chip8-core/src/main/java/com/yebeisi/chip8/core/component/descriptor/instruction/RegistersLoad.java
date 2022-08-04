package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;

@Descriptor
public class RegistersLoad extends RegisterInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xF == codeDigits[0] && 0x6 == codeDigits[2] && 0x5 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        byte vxIndex = getVxIndex();
        short I = registerController.getI();

        for(int i = 0; i <= vxIndex; ++i) {
            short memAddress = (short) (I + i);
            byte value = memoryController.get(memAddress);
            registerController.setRegisterValue(i, value);
        }
    }
}
