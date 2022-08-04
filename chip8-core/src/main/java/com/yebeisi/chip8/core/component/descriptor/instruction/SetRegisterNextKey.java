package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FX0A
 */
@Descriptor
public class SetRegisterNextKey extends RegistersInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xF == codeDigits[0] && 0x0 == codeDigits[2] && 0xA == codeDigits[3];
    }

    @Override
    protected void executeInternally() {
        Keyboard.KeyEnum nextKey = keyboardController.nextKey();
        setVx(nextKey.getCode());
    }

}
