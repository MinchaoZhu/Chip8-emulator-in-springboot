package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;

@Descriptor
public class RegistersMinus2 extends RegistersInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x8 == codeDigits[0] && 0x7 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        int vxInInt = 0xFF & getVx();
        int vyInInt = 0xFF & getVy();
        registerController.setRegisterValue(0xF, vyInInt > vxInInt ? (byte) 1 : 0);
        setVx((byte) (0xFF & (vyInInt - vxInInt)));
    }
}
