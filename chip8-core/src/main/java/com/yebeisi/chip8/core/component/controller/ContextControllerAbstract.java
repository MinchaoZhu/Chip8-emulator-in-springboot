package com.yebeisi.chip8.core.component.controller;

import com.yebeisi.chip8.core.service.context.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class ContextControllerAbstract<T> {

    @Autowired
    ContextService contextService;

    protected abstract T getInstance();

}
