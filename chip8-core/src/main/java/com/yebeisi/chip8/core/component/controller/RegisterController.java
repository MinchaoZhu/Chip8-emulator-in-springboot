package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.common.device.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RegisterController extends ContextControllerAbstract<Register> {

    private static final int StartRegisterIndex = 0;

    private static final int EndRegisterIndex = 15;

    public short getPc() {
        return getInstance().getPc();
    }

    public void setPc(short value) {
        getInstance().setPc(value);
    }

    public short getI() {
        return getInstance().getI();
    }

    public void setI(short value) {
        getInstance().setI(value);
    }

    public byte getRegisterValue(int registerIndex) {
        checkRegisterIndexBound(registerIndex);
        String getMethodName = "get" + "V" + registerIndex;
        try {
            Method getMethod = getInstance().getClass().getMethod(getMethodName);
            return (byte) getMethod.invoke(getInstance());
        } catch (Exception e) {
            throw new RuntimeException("get register value failed: " + e.getMessage());
        }
    }

    public void setRegisterValue(int registerIndex, byte value) {
        checkRegisterIndexBound(registerIndex);
        String setMethodName = "set" + "V" + registerIndex;
        try {
            Method setMethod = getInstance().getClass().getMethod(setMethodName, byte.class);
            setMethod.invoke(getInstance(), value);
        } catch (Exception e) {
            throw new RuntimeException("get register value failed: " + e.getMessage());
        }
    }

    public void initRegister() {
        for(int i = StartRegisterIndex; i <= EndRegisterIndex; ++i) {
            setRegisterValue(i, (byte) 0);
        }
        setI((short) 0);
        setPc((short) 0x200);
    }

    private void checkRegisterIndexBound(int registerIndex) {
        List<Integer> validIndices = IntStream.rangeClosed(StartRegisterIndex, EndRegisterIndex)
                .boxed().collect(Collectors.toList());
        if(!validIndices.contains(registerIndex)) {
            throw new RuntimeException("invalid register index: " + registerIndex);
        }
    }

    @Override
    protected Register getInstance() {
        return contextService.getContext().getRegister();
    }
}
