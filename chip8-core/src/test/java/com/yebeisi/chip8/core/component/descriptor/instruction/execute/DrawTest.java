package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.controller.GraphController;
import com.yebeisi.chip8.core.component.controller.MemoryController;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.Draw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DrawTest extends InstructionBaseTest {

    @Autowired
    private Draw instruction;

    @Autowired
    private GraphController graphController;

    @Autowired
    private MemoryController memoryController;

    @Autowired
    private RegisterController registerController;

    @Test
    public void testExecuteWhenNoCollision() {

        int x = 1;
        int y = 3;
        byte vx = 23;
        byte vy = 23;
        byte I = 0x23;

        // test no collision
        graphController.initGraph();
        byte origin1 = (byte) 0xF0;
        byte sprite1 = (byte) 0x0F;
        for(int j = 0; j < 8; ++j) {
            int bit = (origin1 & (0x1 << (7-j))) >>> (7-j);
            graphController.setGraphMemory((byte) bit, vx, vy + j);
        }

        registerController.setI(I);
        registerController.setRegisterValue(x, vx);
        registerController.setRegisterValue(y, vy);
        registerController.setRegisterValue(0xF, (byte) 0);
        memoryController.set(sprite1, I);

        byte[] code = new byte[] {(byte) 0xD1, 0x31};
        instruction.decode(code);
        instruction.execute();

        int[][] graph = graphController.getGraphMemory();
        for(int j = 0; j < 8; ++j) {
            Assertions.assertEquals(1, graph[vx][vy + j]);
        }
        Assertions.assertEquals(0, registerController.getRegisterValue(0xF));
    }

    @Test
    public void testExecuteWithCollision() {

        int x = 1;
        int y = 3;
        byte vx = 23;
        byte vy = 23;
        byte I = 0x23;

        // test no collision
        graphController.initGraph();
        byte origin1 = (byte) 0xF0;
        byte sprite1 = (byte) 0x1F;
        byte[] expected = new byte[]{1, 1, 1, 0, 1, 1, 1, 1};

        for(int j = 0; j < 8; ++j) {
            int bit = (origin1 & (0x1 << (7-j))) >>> (7-j);
            graphController.setGraphMemory((byte) bit, vx, vy + j);
        }

        registerController.setI(I);
        registerController.setRegisterValue(x, vx);
        registerController.setRegisterValue(y, vy);
        registerController.setRegisterValue(0xF, (byte) 0);
        memoryController.set(sprite1, I);

        byte[] code = new byte[] {(byte) 0xD1, 0x31};
        instruction.decode(code);
        instruction.execute();

        int[][] graph = graphController.getGraphMemory();
        for(int j = 0; j < 8; ++j) {
            Assertions.assertEquals(expected[j], graph[vx][vy + j]);
        }
        Assertions.assertEquals(1, registerController.getRegisterValue(0xF));

    }

}
