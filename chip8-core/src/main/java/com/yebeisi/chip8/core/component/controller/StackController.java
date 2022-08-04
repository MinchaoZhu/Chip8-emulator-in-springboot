package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.device.Stack;
import org.springframework.stereotype.Component;

@Component
public class StackController extends ContextControllerAbstract<Stack>{

    public void initStack() {
        Stack stack = getInstance();
        stack.setSp((short) 0);

        short[] stackData = stack.getStack();
        for(int i = 0; i < stackData.length; ++i) {
            stackData[i] = 0;
        }
    }

    public void push(short value) {
        Stack stack = getInstance();
        short sp = stack.getSp();
        short[] stackData = stack.getStack();
        stackData[sp] = value;
        stack.setSp((short) (1+sp));
    }

    public short pop() {
        Stack stack = getInstance();
        short sp = stack.getSp();
        short[] stackData = stack.getStack();
        short value = stackData[sp-1];
        stack.setSp((short) (sp-1));
        return value;
    }

    public short peek() {
        Stack stack = getInstance();
        short sp = stack.getSp();
        short[] stackData = stack.getStack();
        return stackData[sp-1];

    }

    @Override
    protected Stack getInstance() {
        return contextService.getContext().getStack();
    }
}
