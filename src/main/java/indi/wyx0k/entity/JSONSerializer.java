package indi.wyx0k.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONSerializer implements Serializer {
    ObjectMapper objectMapper = new ObjectMapper();
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T deserialize(Class<T> clz, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes,clz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
