package com.yebeisi.chip8.core.io;

import com.yebeisi.chip8.core.component.controller.MemoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataReader {

    @Autowired
    private MemoryController memoryController;

    public void readData(byte[] data, short index) {
        memoryController.cp(data, index, data.length);
    }

    public void readProgram(byte[] data) {
        readData(data, (short) 0x200);
    }

}
