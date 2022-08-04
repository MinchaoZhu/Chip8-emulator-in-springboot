package com.yebeisi.chip8.common.device;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Register {

    private byte V0 = 0;

    private byte V1 = 0;

    private byte V2 = 0;

    private byte V3 = 0;

    private byte V4 = 0;

    private byte V5 = 0;

    private byte V6 = 0;

    private byte V7 = 0;

    private byte V8 = 0;

    private byte V9 = 0;

    private byte V10 = 0;

    private byte V11 = 0;

    private byte V12 = 0;

    private byte V13 = 0;

    private byte V14 = 0;

    private byte V15 = 0;

    private short I = 0;

    private short pc = 0;

}
