package com.yebeisi.chip8.common.data;

import com.yebeisi.chip8.common.device.Graph;
import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.common.device.Memory;
import com.yebeisi.chip8.common.device.Register;
import com.yebeisi.chip8.common.device.Stack;
import com.yebeisi.chip8.common.device.Timer;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class Context {
    private Map<String, InstructionContext> instructionContexts;

    private Graph graph;

    private Memory memory;

    private Register register;

    private Stack stack;

    private Timer timer;

    private Keyboard keyboard;

    public Context() {
        instructionContexts = new HashMap<>();
        graph = new Graph();
        memory = new Memory();
        register = new Register();
        stack = new Stack();
        timer = new Timer();
        keyboard = new Keyboard();
    }

}
