package com.yebeisi.chip8.core.component.descriptor.instruction.execute;

import com.yebeisi.chip8.core.CoreBaseTest;
import com.yebeisi.chip8.core.component.controller.GraphController;
import com.yebeisi.chip8.core.component.controller.KeyboardController;
import com.yebeisi.chip8.core.component.controller.MemoryController;
import com.yebeisi.chip8.core.component.controller.RegisterController;
import com.yebeisi.chip8.core.component.controller.StackController;
import com.yebeisi.chip8.core.component.controller.TimerController;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.BaseInstructionExecutor;
import com.yebeisi.chip8.core.service.context.ContextService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class InstructionBaseTest extends CoreBaseTest {

    @Autowired
    protected ContextService contextService;

    @Autowired
    protected RegisterController registerController;

    @Autowired
    protected StackController stackController;

    @Autowired
    protected GraphController graphController;

    @Autowired
    protected KeyboardController keyboardController;

    @Autowired
    protected TimerController timerController;

    @Autowired
    protected MemoryController memoryController;

    private static final String VX_INDEX_KEY = "vx_index_key";

    private static final String VY_INDEX_KEY = "vy_index_key";

    private static final String N_KEY = "n_key";

    private static final String NN_KEY = "nn_key";

    private static final String NNN_KEY = "nnn_key";

    private <T> T getContextValue(BaseInstructionExecutor instruction, String key) {
        return (T) contextService.getInstructionContext(instruction.getClass().getName()).getContext().get(key);
    }

    protected byte getVxIndex(BaseInstructionExecutor instruction) {
        return getContextValue(instruction, VX_INDEX_KEY);
    }

    protected byte getVyIndex(BaseInstructionExecutor instruction) {
        return getContextValue(instruction, VY_INDEX_KEY);
    }

    protected byte getVx(BaseInstructionExecutor instruction) {
        return registerController.getRegisterValue(getVxIndex(instruction));
    }

    protected byte getVy(BaseInstructionExecutor instruction) {
        return registerController.getRegisterValue(getVyIndex(instruction));
    }

    protected byte getN(BaseInstructionExecutor instruction) {
        return getContextValue(instruction, N_KEY);
    }

    protected byte getNN(BaseInstructionExecutor instruction) {
        return getContextValue(instruction, NN_KEY);
    }

    protected short getNNN(BaseInstructionExecutor instruction) {
        return getContextValue(instruction, NNN_KEY);
    }

}
