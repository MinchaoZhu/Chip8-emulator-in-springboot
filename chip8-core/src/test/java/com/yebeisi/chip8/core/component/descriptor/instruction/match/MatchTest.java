package com.yebeisi.chip8.core.component.descriptor.instruction.match;

import com.yebeisi.chip8.common.utils.BeanUtils;
import com.yebeisi.chip8.common.utils.JsonUtils;
import com.yebeisi.chip8.core.CoreBaseTest;
import com.yebeisi.chip8.core.component.descriptor.instruction.type.BaseInstructionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class MatchTest extends CoreBaseTest {

    private Map<String, List<byte[]>> testSets;

    private static final String testSetPath = "test/instruction_match_test_set.json";

    @BeforeEach
    public void initTestSet() throws IOException {
        testSets = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(testSetPath)).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));
        Map<String, List> rawRestSet = JsonUtils.parseMap(content, String.class, List.class);
        rawRestSet.forEach((key, value) -> {
            List<byte[]> testSet = (List<byte[]>) value.stream()
                    .map(MatchTest::convertStringArrToHexArr)
                    .collect(Collectors.toList());
            testSets.put(key, testSet);
        });
    }

    public static byte[] convertStringArrToHexArr(Object codeObject) {
        String code = (String) codeObject;
        Assertions.assertEquals(code.length(), 4);
        byte[] result = new byte[2];
        String byte0 = "0x" + code.substring(0, 2);
        String byte1 = "0x" + code.substring(2, 4);
        int v0 = Integer.decode(byte0);
        byte b0 = (byte) v0;
        result[0] = b0;
        int v1 = Integer.decode(byte1);
        byte b1 = (byte) v1;
        result[1] = b1;
        return result;
    }

    @Test
    public void testMatch() {
        Map<String, BaseInstructionExecutor> executors = new HashMap<>();
        testSets.forEach((key, value) ->{
            String name = Character.toLowerCase(key.charAt(0)) + key.substring(1);
            BaseInstructionExecutor executor = (BaseInstructionExecutor) BeanUtils.getBean(name);
            executors.put(key, executor);
        });

        executors.forEach((key1, executor) -> {
            testSets.forEach((key2, testSet) -> {
                boolean expected = StringUtils.equals(key1,key2);
                testSet.forEach(input -> {
                    boolean actual = executor.match(input);
                    if(expected != actual) {
                        log.info("instruction executor: " + key1
                                + ", instruction: " + key2
                                + ", code: 0x" + String.format("%02X", input[0]) + String.format("%02X", input[1])
                                + ", match expected: " + expected
                                + ", actual: " + actual);
                    }

                    Assertions.assertEquals(expected, actual);
                });
            });
        });
    }

}
