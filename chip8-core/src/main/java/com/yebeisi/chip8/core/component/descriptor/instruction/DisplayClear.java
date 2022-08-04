package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.GraphController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.BaseInstructionExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Descriptor
public class DisplayClear extends BaseInstructionExecutor {

    @Autowired
    private GraphController graphController;

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        byte[] callInstruction = {0x0, 0x0, 0xE, 0x0};
        return Arrays.equals(codeDigits, callInstruction);
    }

    @Override
    protected void decodeInternally(byte[] code) {}

    @Override
    protected void executeInternally() {
        graphController.initGraph();
    }

}
