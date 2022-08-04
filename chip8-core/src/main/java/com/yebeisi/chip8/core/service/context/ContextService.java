package com.yebeisi.chip8.core.service.context;

import com.yebeisi.chip8.common.data.Context;
import com.yebeisi.chip8.common.data.InstructionContext;
import org.springframework.stereotype.Service;

@Service
public interface ContextService {

    void initContextThreadLocally();

    Context getContext();

    InstructionContext getInstructionContext(String instructionName);

}
