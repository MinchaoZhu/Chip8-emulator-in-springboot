package com.yebeisi.chip8.core.component.descriptor.instruction;

import com.yebeisi.chip8.common.utils.OpCodeUtils;
import com.yebeisi.chip8.core.annotation.Descriptor;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.RegistersInstruction;

@Descriptor
public class Draw extends RegistersInstruction {

    @Override
    protected boolean matchInternally(byte[] code) {
        byte[] codeDigits = OpCodeUtils.getDigits(code);
        return 0xD == codeDigits[0];
    }

    @Override
    protected void executeInternally() {
        byte vx = getVx();
        byte vy = getVy();
        int n = getN();
        short I = registerController.getI();

        int[][] sprites = new int[n][8];
        for(short y = 0; y < n; ++y) {
            byte graphBits = memoryController.get((short) (I + y));
            for(int x = 0; x < 8; ++x) {
                int mask = 1 << (7 - x);
                int wantedBit = graphBits & mask;
                wantedBit = wantedBit >>> (7 - x);

                sprites[y][x] = wantedBit;
            }
        }

        for(int y = 0; y < n; ++y) {
            for(int x = 0; x < 8; ++x) {
                int targetX = vx + x;
                int targetY = vy + y;
                int currentBit = graphController.getGraphMemory()[targetY][targetX];
                int writtenBit = (sprites[y][x] & 0x1) ^ (currentBit & 0x1);
                if(currentBit != 0 && writtenBit == 0) {
                    registerController.setRegisterValue(0xF, (byte) 1);
                }
                graphController.setGraphMemory((byte) writtenBit, targetY, targetX);
            }
        }
    }
}
