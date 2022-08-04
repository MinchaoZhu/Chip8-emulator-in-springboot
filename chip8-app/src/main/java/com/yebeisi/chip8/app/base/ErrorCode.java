package com.yebeisi.chip8.app.base;

public enum ErrorCode {

    SUCCESS(0, "SUCCESS"),
    FAIL(200, "FAIL")
    ;

    private final int code;
    private final String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
