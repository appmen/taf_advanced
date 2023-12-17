package com.learning.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Utils {

    public static String getAuthHeader(String user, String password){
        byte[] bytesToEncode = (user + ":" + password)
                                .getBytes(StandardCharsets.UTF_8);
        return "Basic " + Base64.getEncoder().encodeToString(bytesToEncode);
    }

    public static <T> T getErrorResponse(String responseBody, Class<T> cls) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, cls);
    }
}
