package com.yebeisi.chip8.core.component.descriptor.instruction.type;

import com.yebeisi.chip8.common.utils.OpCodeUtils;

public abstract class RegisterInstruction extends BaseInstructionExecutor {

    @Override
    protected void decodeInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        byte vxIndex = codeDigits[1];
        setContextValue(VX_INDEX_KEY, vxIndex);
    }

    protected byte getVxIndex() {
        return getContextValue(VX_INDEX_KEY);
    }

    protected byte getVx() {
        return registerController.getRegisterValue(getVxIndex());
    }

    protected void setVx(byte vx) {
        registerController.setRegisterValue(getVxIndex(), vx);
    }

}
