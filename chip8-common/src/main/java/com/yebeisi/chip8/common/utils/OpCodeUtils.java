package com.yebeisi.chip8.common.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class OpCodeUtils {

    private final static byte digitMask = 0x0F;

    /**
     * get digits in hex in big endian
     * code = 1FA2
     * code in bytes = [0x1F, 0xA2]
     * code in digits = [0x1, 0xF, 0xA, 0x2]
     * @param codeIn2Bytes code in two bytes
     * @return
     */
    public static byte[] getDigits(byte[] codeIn2Bytes) {
        if(Array.getLength(codeIn2Bytes) != 2) {
            throw new RuntimeException("OpCode parse digits failed: code: " + Arrays.toString(codeIn2Bytes) + ", length is not 2");
        }
        byte[] result = new byte[4];
        result[0] = (byte) (digitMask & (codeIn2Bytes[0] >>> 4));
        result[1] = (byte) (digitMask & codeIn2Bytes[0]);
        result[2] = (byte) (digitMask & (codeIn2Bytes[1] >>> 4));
        result[3] = (byte) (digitMask & codeIn2Bytes[1]);
        return result;
    }

    /**
     * generate value from bytes array
     * {0x1, 0xF, 0x2}
     * ->
     * 0x1F2
     * @param addressBytes address bytes array
     * @return address value
     */
    public static short getShortFromBytes(byte[] addressBytes) {
        int len = addressBytes.length;
        if(len <= 0 || len > 4) {
            throw new RuntimeException("valid bytest length: " + addressBytes.length);
        }

        short address = 0x0;
        address |= addressBytes[0];

        for(int i = 1; i < len; ++i) {
            address <<= 4;
            address |= addressBytes[i];
        }
        return address;
    }

    /**
     * parse code to short value
     * @param codeIn4Bytes
     * @return
     */
    public static short getCodeFromBytes(byte[] codeIn4Bytes) {
        if(Array.getLength(codeIn4Bytes) != 4) {
            throw new RuntimeException("OpCode parse digits failed: code: " + Arrays.toString(codeIn4Bytes) + ", length is not 4");
        }
        short value = 0x0;
        value |= codeIn4Bytes[0];
        value <<= 4;
        value |= codeIn4Bytes[1];
        value <<= 4;
        value |= codeIn4Bytes[2];
        value <<= 4;
        value |= codeIn4Bytes[3];

        return value;
    }


}
