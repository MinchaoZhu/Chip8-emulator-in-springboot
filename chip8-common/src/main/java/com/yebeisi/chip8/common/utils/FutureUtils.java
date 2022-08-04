package com.yebeisi.chip8.common.utils;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureUtils {

    private FutureUtils() {}

    public static <T> T getResultOrNull(Future<T> future) {
        try {
            return future.get(30, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getResultOrNull(Future<T> future, Integer timeout) {
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return null;
        }
    }

}
