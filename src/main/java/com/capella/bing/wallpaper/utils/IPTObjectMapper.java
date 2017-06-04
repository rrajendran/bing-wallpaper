package com.capella.bing.wallpaper.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class IPTObjectMapper {
    private static ObjectMapper objectMapper;

    public IPTObjectMapper() {
    }

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
       /*     objectMapper.registerModule(new Jdk8Module());
            objectMapper.registerModule(new JSR310Module());
            objectMapper.registerModule(new JodaModule());*/
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        return objectMapper;
    }
}