package com.yebeisi.chip8.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public final class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
    }

    private JsonUtils() {
    }

    public static <T> Set<T> parseSet(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr,
                    objectMapper.getTypeFactory().constructCollectionType(HashSet.class, clazz));
        } catch (Exception e) {
            error(jsonStr, e, clazz);
        }
        return new HashSet<>();
    }

    public static Set<String> parseSet(String jsonStr) {
        return parseSet(jsonStr, String.class);
    }

    public static <T> List<T> parseList(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            error(jsonStr, e, clazz);
        }
        return new ArrayList<>();
    }



    public static List<Long> parseList(String jsonStr) {
        return parseList(jsonStr, Long.class);
    }

    public static <K, V> Map<K, V> parseMap(String jsonStr, Class<K> k, Class<V> v) {
        try {
            return objectMapper.readValue(jsonStr,
                    objectMapper.getTypeFactory().constructMapType(HashMap.class, k, v));
        } catch (Exception e) {
            error(jsonStr, e, k, v);
        }
        return new HashMap<>();
    }


    public static Map<String, Object> parseMap2Object(String jsonStr) {
        return parseMap(jsonStr, String.class, Object.class);
    }

    public static Map<String, String> parseMap2String(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<Map>() {});
        } catch (Exception e) {
            error(jsonStr, e);
        }
        return new HashMap<>();
    }

    public static <T> T parse(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper
                    .readValue(jsonStr, objectMapper.getTypeFactory().constructType(clazz));
        } catch (Exception e) {
            error(jsonStr, e, clazz);
        }
        return null;
    }


    public static String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            log.error("get json string failed", e);
            return "null";
        }
    }

    public static String[] toJsonArray(Object... details) {
        String[] newDetails = new String[1];
        try {
            newDetails = Stream.of(details).map(JsonUtils::toJson).toArray(String[]::new);
        } catch (Exception e) {
            log.info("failed at toJson", e);
            newDetails = Stream.of(details).map(String::valueOf).toArray(String[]::new);
        }
        return newDetails;
    }

    private static void error(String jsonStr, Exception e, Class... details) {
        String errorMsg = String.format("parsing string \"%s\" failed to target type: %s", jsonStr,
                String.join(", ", Stream.of(details).map(d -> d.getSimpleName()).toArray(String[]::new)));
        log.info(errorMsg, e);
    }

    public static <T> T convert(Map<String, Object> aMap, Class<T> t) {
        try {
            return objectMapper
                    .convertValue(aMap, objectMapper.getTypeFactory().constructType(t));
        } catch (Throwable e) {
            log.error("converting failed! aMap: {}, class: {}", toJson(aMap), t.getClass().getSimpleName(), e);
        }
        return null;
    }
}
