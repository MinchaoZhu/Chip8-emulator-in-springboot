package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.device.Keyboard;
import com.yebeisi.chip8.core.CoreBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeyboardControllerTest extends CoreBaseTest {

    @Autowired
    KeyboardController keyboardController;

    @Test
    public void testKeyboard() {
        keyboardController.press(Keyboard.KeyEnum.Key_1);
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));

        keyboardController.press(Keyboard.KeyEnum.Key_2);
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));

        keyboardController.release(Keyboard.KeyEnum.Key_F);
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));

        keyboardController.release(Keyboard.KeyEnum.Key_1);
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));

        keyboardController.press(Keyboard.KeyEnum.Key_F);
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_F));

        keyboardController.release(Keyboard.KeyEnum.Key_2);
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));
        Assertions.assertTrue(keyboardController.isPressed(Keyboard.KeyEnum.Key_F));

        keyboardController.release(Keyboard.KeyEnum.Key_F);
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_1));
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_2));
        Assertions.assertFalse(keyboardController.isPressed(Keyboard.KeyEnum.Key_F));
    }

}
