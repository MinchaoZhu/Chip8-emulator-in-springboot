package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.device.Keyboard;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KeyboardController extends ContextControllerAbstract<Keyboard>{

    private static final Long SLEEP_LOOP_TIME_IN_MILLIS = 100L;

    public void press(Keyboard.KeyEnum key) {
        int code = key.getCode();
        getInstance().getKeyFlags()[code] = true;
        getInstance().setNext(key);
    }

    public void release(Keyboard.KeyEnum key) {
        int code = key.getCode();
        getInstance().getKeyFlags()[code] = false;
    }

    public boolean isPressed(Keyboard.KeyEnum key) {
        int code = key.getCode();
        return getInstance().getKeyFlags()[code];
    }

    /**
     * await for next key pressed
     * @return
     */
    public Keyboard.KeyEnum nextKey() {
        try {
            getInstance().setNext(null);
            while(Objects.isNull(getInstance().getNext())) {
                Thread.sleep(SLEEP_LOOP_TIME_IN_MILLIS);
            }
            return getInstance().getNext();
        } catch (Exception e) {
            return Keyboard.KeyEnum.Key_0; // if any exception throws, return Key_0 to avoid thread collapsing
        }
    }

    @Override
    protected Keyboard getInstance() {
        return contextService.getContext().getKeyboard();
    }
}
