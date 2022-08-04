package com.yebeisi.chip8.core.component.descriptor;

import com.yebeisi.chip8.core.component.controller.MemoryController;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.BaseInstructionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Descriptor {

    @Autowired
    List<BaseInstructionExecutor> executors;

    @Autowired
    RegisterController registerController;

    @Autowired
    MemoryController memoryController;

    /**
     * 执行一次 fetch, execute 的循环
     */
    public void execute() {
        byte[] code = fetchCode();
        updatePc();
        executeInstruction(code);
    }

    private void executeInstruction(byte[] code) {
        List<BaseInstructionExecutor> matched = executors.stream()
                .filter(executor -> executor.match(code))
                .collect(Collectors.toList());
        if(matched.size() != 1) {
            throw new RuntimeException("more than one matched instructions for code: " + code + ", executors: " + matched);
        }
        matched.get(0).decode(code);
        matched.get(0).execute();
    }

    /**
     * update program counter
     */
    private void updatePc() {
        short currentPc = registerController.getPc();
        short newPc = (short) (currentPc + 2);
        registerController.setPc(newPc);
    }

    private byte[] fetchCode() {
        short currentPc = registerController.getPc();
        return memoryController.getBytes(currentPc, 2);
    }




}
