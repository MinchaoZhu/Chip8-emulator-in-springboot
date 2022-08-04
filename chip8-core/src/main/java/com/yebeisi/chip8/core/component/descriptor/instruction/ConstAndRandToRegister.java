package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegisterConstInstruction;
import org.apache.commons.lang3.RandomUtils;

@Descriptor
public class ConstAndRandToRegister extends RegisterConstInstruction {
    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xC == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        byte rand = RandomUtils.nextBytes(1)[0];
        setVx((byte) (rand & getNN()));
    }
}
