package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.BaseInstructionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * call
 * format: 0x0NNN (excludes 0x00E0, 0x00EE)
 * result: set pc as NNN
 */
@Slf4j
@Descriptor
public class Call extends BaseInstructionExecutor {

    @Autowired
    RegisterController registerController;

    private final static String ADDRESS_KEY = "address_key";

    @Override
    protected boolean matchInternally(byte[] code) {
        List<byte[]> excludes = Arrays.asList(
            new byte[] {0x00, (byte) 0xE0}, // for Clears the screen
            new byte[] {0x00, (byte) 0xEE}  // for Returns from a subroutine.
        );
        // exclude two exceptions
        for(byte[] exclude : excludes) {
            if(exclude[0] == code[0] && exclude[1] == code[1]) {
                return false;
            }
        }

        byte[] codeDigits = OpCodeUtils.getDigits(code);

        return 0x0 == codeDigits[0];
    }

    @Override
    protected void decodeInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        byte[] addressBytes = new byte[] {codeDigits[1], codeDigits[2], codeDigits[3]};
        setContextValue(ADDRESS_KEY, addressBytes);
    }

    @Override
    protected void executeInternally() {
        byte[] addressBytes = getContextValue(ADDRESS_KEY);
        short address = OpCodeUtils.getShortFromBytes(addressBytes);
        registerController.setPc(address);
    }

}
