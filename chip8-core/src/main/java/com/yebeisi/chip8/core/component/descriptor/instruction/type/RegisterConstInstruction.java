package com.yebeisi.chip8.core.component.descriptor.instruction.type;

import com.yebeisi.chip8.common.utils.OpCodeUtils;

public abstract class RegisterConstInstruction extends BaseInstructionExecutor{

    @Override
    protected void decodeInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        byte vxIndex = codeDigits[1];
        byte nn = (byte) OpCodeUtils.getShortFromBytes(new byte[] {codeDigits[2], codeDigits[3]});
        setContextValue(VX_INDEX_KEY, vxIndex);
        setContextValue(NN_KEY, nn);
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

    protected byte getNN() {
        return getContextValue(NN_KEY);
    }
}
