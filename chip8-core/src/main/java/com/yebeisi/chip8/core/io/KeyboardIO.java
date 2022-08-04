package com.yebeisi.chip8.core.io;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.core.component.controller.KeyboardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeyboardIO {

    @Autowired
    private KeyboardController keyboardController;

    public void pressKey(Keyboard.KeyEnum key) {
        keyboardController.press(key);
    }

    public void releaseKey(Keyboard.KeyEnum key) {
        keyboardController.release(key);
    }

}
