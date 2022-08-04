package com.yebeisi.chip8.common.device;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Timer {

    private byte delayTimer;

    private byte soundTimer;

}
