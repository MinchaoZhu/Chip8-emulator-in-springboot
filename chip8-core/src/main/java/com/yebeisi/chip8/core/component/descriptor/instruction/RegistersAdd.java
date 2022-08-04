package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;

@Descriptor
public class RegistersAdd extends RegistersInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x8 == codeDigits[0] && 0x4 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        int vxInInt = 0xFF & getVx();
        int vyInInt = 0xFF & getVy();
        int added = vxInInt + vyInInt;
        registerController.setRegisterValue(0xF, added > 0xFF ? (byte) 1 : 0);
        setVx((byte) (getVx() + getVy()));
    }
}
