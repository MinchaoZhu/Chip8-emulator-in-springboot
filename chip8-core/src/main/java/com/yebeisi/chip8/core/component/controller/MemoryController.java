package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.config.FontSetConfig;
import com.yebeisi.chip8.common.device.Memory;
import org.springframework.stereotype.Component;

/**
 *                   字符集                 指令部分
 * |----------|-----------------|------------------------------------|
 * 0         0x50              0x200                                0xFFF
 *
 */

@Component
public class MemoryController extends ContextControllerAbstract<Memory>{

    public byte get(short index) {
        return getInstance().getMemory()[index];
    }

    public byte[] getBytes(short index, int length) {
        byte[] result = new byte[length];

        for(int i = 0; i < length; ++i) {
            result[i] = getInstance().getMemory()[index + i];
        }
        return result;
    }

    public void set(byte ch, short index) {
        getInstance().getMemory()[index] = ch;
    }

    public void cp(byte[] src, short index, int length) {
        if (length >= 0) System.arraycopy(src, 0, getInstance().getMemory(), index, length);
    }

    public void fill(byte ch, short index, int length) {
        for(int i = 0; i < length; ++i) set(ch, (short) (i + index));
    }

    public void initMemory() {
        fill((byte) 0, (short) 0, getInstance().getMemory().length);

        // init FontSet mem
        byte[] fontSet = FontSetConfig.getFontSet();
        cp(fontSet, FontSetConfig.getFontSetMemAddress(), fontSet.length);
    }

    @Override
    protected Memory getInstance() {
        return contextService.getContext().getMemory();
    }

}
