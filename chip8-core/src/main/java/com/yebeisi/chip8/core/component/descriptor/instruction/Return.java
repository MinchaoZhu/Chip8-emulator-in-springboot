package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.controller.StackController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.BaseInstructionExecutor;
import org.springframework.beans.factory.annotation.Autowired;

@Descriptor
public class Return extends BaseInstructionExecutor {

    @Autowired
    private RegisterController registerController;

    @Autowired
    private StackController stackController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] digits = OpCodeUtils.getDigits(code);
        short codeValue = OpCodeUtils.getCodeFromBytes(digits);
        short returnCode = 0x00EE;
        return returnCode == codeValue;
    }

    @Override
    protected void decodeInternally(byte[] code) {}

    @Override
    protected void executeInternally() {
        short returnAddress = stackController.pop();
        registerController.setPc(returnAddress);
    }

}
