package com.yebeisi.chip8.common.data;

import lombok.Data;

import java.util.Map;

@Data
public class InstructionContext {

    public final static String DECODING = "decoding";

    public final static String DECODED = "decoded";

    public final static String EXECUTING = "executing";

    public final static String FINISHED = "finished";

    private String status; // 当前指令执行的状态

    private Map<String, Object> context;

}
