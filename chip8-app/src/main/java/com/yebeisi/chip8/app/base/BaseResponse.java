package com.yebeisi.chip8.app.base;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 89648964L;
    private boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T result;

    public static <T> BaseResponseBuilder<T> newSuccessResponse() {
        return BaseResponse.<T>builder()
                .success(true)
                .errorCode(ErrorCode.SUCCESS.getCode())
                .errorMsg(ErrorCode.SUCCESS.getDesc());
    }

    public static <T> BaseResponseBuilder<T> newFailResponse() {
        return BaseResponse.<T>builder()
                .success(false)
                .errorCode(ErrorCode.FAIL.getCode())
                .result(null);
    }


}
