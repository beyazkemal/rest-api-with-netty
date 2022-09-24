package com.kemalbeyaz.netty.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper OM = new ObjectMapper();

    static {
        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OM.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
    }

    public static <T> String writeAsJson(Class<T> clazz, T data) throws JsonProcessingException {
        return OM.writerFor(clazz).writeValueAsString(data);
    }

    public static <T> byte[] writeAsByte(Class<T> clazz, T data) throws JsonProcessingException {
        return OM.writerFor(clazz).writeValueAsBytes(data);
    }

    public static <T> T readJson(Class<T> clazz, String data) throws JsonProcessingException {
        return OM.readerFor(clazz).readValue(data);
    }

    public static <T> T readJson(Class<T> clazz, byte[] data) throws IOException {
        return OM.readerFor(clazz).readValue(data);
    }

    private JsonUtil() {
    }
}
