package com.yebeisi.chip8.core.component.descriptor.instruction.type;

import com.yebeisi.chip8.common.data.InstructionContext;
import com.yebeisi.chip8.core.annotation.ArrayLen;
import com.yebeisi.chip8.core.component.controller.GraphController;
import com.yebeisi.chip8.core.component.controller.KeyboardController;
import com.yebeisi.chip8.core.component.controller.MemoryController;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.controller.TimerController;
import com.yebeisi.chip8.core.service.context.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * NNN: address
 * NN: 8-bit constant
 * N: 4-bit constant
 * X and Y: 4-bit register identifier
 * PC : Program Counter
 * I : 16bit register (For memory address) (Similar to void pointer);
 * VN: One of the 16 available variables. N may be 0 to F (hexadecimal);
 */
@Component
public abstract class BaseInstructionExecutor {

    @Autowired
    protected ContextService contextService;

    @Autowired
    protected RegisterController registerController;

    @Autowired
    protected MemoryController memoryController;

    @Autowired
    protected GraphController graphController;

    @Autowired
    protected KeyboardController keyboardController;

    @Autowired
    protected TimerController timerController;

    protected static final String VX_INDEX_KEY = "vx_index_key";

    protected static final String VY_INDEX_KEY = "vy_index_key";

    protected static final String N_KEY = "n_key";

    protected static final String NN_KEY = "nn_key";

    protected static final String NNN_KEY = "nnn_key";

    @ArrayLen(2)
    public boolean match(byte[] code) {
        return matchInternally(code);
    }

    @ArrayLen(2)
    public void decode(byte[] code) {
        decodeInternally(code);
    }

    public void execute() {
        executeInternally();
    }

    protected <T> T getContextValue(String key) {
        return (T) contextService.getInstructionContext(this.getClass().getName()).getContext().get(key);
    }

    protected void setContextValue(String key, Object value) {
        initContextValueIfAbsent();
        contextService.getInstructionContext(this.getClass().getName()).getContext().put(key, value);
    }

    private void initContextValueIfAbsent() {
        Map<String, InstructionContext> instructionContext = contextService.getContext().getInstructionContexts();
        if(!instructionContext.containsKey(this.getClass().getName())) {
            instructionContext.put(this.getClass().getName(), new InstructionContext());
            instructionContext.get(this.getClass().getName()).setContext(new HashMap<>());
        }
    }

    /**
     * all code executor must override it
     * @param code two-bytes long op code
     * @return matched
     */
    protected abstract boolean matchInternally(byte[] code);

    /**
     * all code executor must override it
     * @param code two-bytes long op code
     */
    protected abstract void decodeInternally(byte[] code);

    /**
     * all code executor must override it
     */
    protected abstract void executeInternally();

}
