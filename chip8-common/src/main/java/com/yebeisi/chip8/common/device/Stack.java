package com.yebeisi.chip8.common.device;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Stack {

    private short[] stack = new short[512];

    private short sp;

}
