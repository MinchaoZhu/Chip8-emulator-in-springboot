package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.component.descriptor.instruction.DisplayClear;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DisplayClearTest extends InstructionBaseTest {


    @Autowired
    private DisplayClear instruction;

    @Test
    public void testExecute() {

        List<Pair<Integer, Integer>> positions = Arrays.asList(
                Pair.of(23, 5), Pair.of(1, 2), Pair.of(25, 14)
        );

        positions.forEach(pair -> {
            graphController.setGraphMemory((byte) 1, pair.getLeft(), pair.getRight());
        });

        int[][] mem = graphController.getGraphMemory();
        positions.forEach(pair -> {
            int actual = mem[pair.getLeft()][pair.getRight()];
            Assertions.assertEquals(1, actual);
        });


        instruction.decode(new byte[] {0x00, (byte) 0xE0});
        instruction.execute();
        int[][] mem2 = graphController.getGraphMemory();
        for(int i = 0; i < mem2.length; ++i) {
            for(int j = 0; j < mem2[0].length; ++j) {
                Assertions.assertEquals(0, mem[i][j]);
            }
        }
    }


}
