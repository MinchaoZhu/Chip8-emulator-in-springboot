package com.yebeisi.chip8.core.component.descriptor.instruction.type;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ConstInstruction extends BaseInstructionExecutor {

    @Override
    protected void decodeInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        byte[] nBytes = new byte[] {codeDigits[1], codeDigits[2], codeDigits[3]};
        short n = OpCodeUtils.getShortFromBytes(nBytes);
        setContextValue(NNN_KEY, n);
    }

    protected short getNNN() {
        return getContextValue(NNN_KEY);
    }

}
