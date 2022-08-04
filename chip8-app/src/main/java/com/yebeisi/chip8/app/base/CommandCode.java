package com.yebeisi.chip8.app.base;

public enum CommandCode {

    RUN("run", "start the program"),
    PAUSE("pause", "pause the program"),
    STOP("stop", "stop the program"),
    RESUME("resume", "resume the program"),
    INIT("init", "reset the program"),
    ;

    private final String code;

    private final String desc;

    CommandCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
