package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.device.Graph;
import org.springframework.stereotype.Component;

@Component
public class GraphController extends ContextControllerAbstract<Graph> {

    @Override
    protected Graph getInstance() {
        return contextService.getContext().getGraph();
    }

    public void initGraph() {
        Graph graph = getInstance();
        int[][] gfx = graph.getGfx();

        for(int i = 0; i < gfx.length; ++i) {
            for(int j = 0; j < gfx[0].length; ++j) {
                gfx[i][j] = 0;
            }
        }
    }

    public int[][] getGraphMemory() {
        Graph graph = getInstance();
        return graph.getGfx();
    }

    public void setGraphMemory(byte value, int i, int j) {
        Graph graph = getInstance();
        int[][] gfx = graph.getGfx();
        gfx[i][j] = value;
    }

}
