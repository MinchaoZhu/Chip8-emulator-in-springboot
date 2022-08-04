package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.device.Timer;
import org.springframework.stereotype.Component;

@Component
public class TimerController extends ContextControllerAbstract<Timer> {

    public void timerDecrease() {
        byte delayTimer = getInstance().getDelayTimer();
        byte soundTimer = getInstance().getSoundTimer();

        if(delayTimer > 0) {
            getInstance().setDelayTimer((byte) (delayTimer - 1));
        }

        if(soundTimer > 0) {
            getInstance().setSoundTimer((byte) (soundTimer - 1));
        }

    }

    public void initTimer() {
        getInstance().setDelayTimer((byte) 0);
        getInstance().setSoundTimer((byte) 0);
    }

    public byte getDelayTimer() {
        return getInstance().getDelayTimer();
    }

    public void setDelayTimer(byte delayTimer) {
        getInstance().setDelayTimer(delayTimer);
    }

    public byte getSoundTimer() {
        return getInstance().getSoundTimer();
    }

    public void setSoundTimer(byte soundTimer) {
        getInstance().setSoundTimer(soundTimer);
    }

    @Override
    protected Timer getInstance() {
        return contextService.getContext().getTimer();
    }
}
