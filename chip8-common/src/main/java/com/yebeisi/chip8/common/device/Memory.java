package com.yebeisi.chip8.common.device;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
public class Memory {

    private byte[] memory = new byte[4096];

}
