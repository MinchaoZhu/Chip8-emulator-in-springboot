package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;

@Descriptor
public class SkipIfNotPressed extends RegisterInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xE == codeDigits[0] && 0xA == codeDigits[2] && 0x1 == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        byte vx = getVx();
        Keyboard.KeyEnum keyEnum = Keyboard.KeyEnum.getKeyEnumFromCode(vx);
        if(! keyboardController.isPressed(keyEnum)) {
            short currentPc = registerController.getPc();
            registerController.setPc((short) (currentPc + 2));
        }
    }
}
