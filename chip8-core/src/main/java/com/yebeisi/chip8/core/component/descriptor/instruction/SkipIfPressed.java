package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterInstruction;

@Descriptor
public class SkipIfPressed extends RegisterInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xE == codeDigits[0] && 0x9 == codeDigits[2] && 0xE == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        byte vx = getVx();
        Keyboard.KeyEnum keyEnum = Keyboard.KeyEnum.getKeyEnumFromCode(vx);
        if(keyboardController.isPressed(keyEnum)) {
            short currentPc = registerController.getPc();
            registerController.setPc((short) (currentPc + 2));
        }
    }
}
