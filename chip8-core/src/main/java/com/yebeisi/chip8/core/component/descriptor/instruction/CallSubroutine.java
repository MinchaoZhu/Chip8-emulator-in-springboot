package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.controller.StackController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.ConstInstruction;
import org.springframework.beans.factory.annotation.Autowired;

@Descriptor
public class CallSubroutine extends ConstInstruction {

    @Autowired
    private RegisterController registerController;

    @Autowired
    private StackController stackController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0x2 == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        short targetAddress = getNNN();
        short currentPc = registerController.getPc();
        stackController.push(currentPc);
        registerController.setPc(targetAddress);
    }

}
