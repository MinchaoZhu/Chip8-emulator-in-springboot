package com.yebeisi.chip8.core.component.descriptor.instruction.type;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class RegistersInstruction extends BaseInstructionExecutor{

    @Override
    protected void decodeInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        byte vxIndex = codeDigits[1];
        byte vyIndex = codeDigits[2];
        byte n = codeDigits[3];
        setContextValue(VX_INDEX_KEY, vxIndex);
        setContextValue(VY_INDEX_KEY, vyIndex);
        setContextValue(N_KEY, n);
    }

    protected byte getVxIndex() {
        return getContextValue(VX_INDEX_KEY);
    }

    protected byte getVyIndex() {
        return getContextValue(VY_INDEX_KEY);
    }

    protected byte getN() {
        return getContextValue(N_KEY);
    }

    protected byte getVx() {
        return registerController.getRegisterValue(getVxIndex());
    }

    protected byte getVy() {
        return registerController.getRegisterValue(getVyIndex());
    }

    protected void setVx(byte vx) {
        registerController.setRegisterValue(getVxIndex(), vx);
    }

    protected void setVy(byte vy) {
        registerController.setRegisterValue(getVyIndex(), vy);
    }

}
