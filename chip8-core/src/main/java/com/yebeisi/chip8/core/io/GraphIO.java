package com.yebeisi.chip8.core.io;

import com.yebeisi.chip8.core.component.controller.GraphController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphIO {

    @Autowired
    private GraphController graphController;

    public int[][] getGraphMemory() {
        return graphController.getGraphMemory();
    }

}
