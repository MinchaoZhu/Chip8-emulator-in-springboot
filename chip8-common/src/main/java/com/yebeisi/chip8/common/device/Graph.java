package com.yebeisi.chip8.common.device;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Graph {

    private int[][] gfx = new int[32][64];  // graph pixel map

}
