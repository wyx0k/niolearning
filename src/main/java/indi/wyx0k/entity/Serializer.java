package indi.wyx0k.entity;

public interface Serializer {
    byte JSONSerialier = 1;
    Serializer DEFAULT = new JSONSerializer();
    byte getSerializerAlgorithm();
    byte [] serialize(Object object);
    <T> T deserialize(Class<T> clz , byte[] bytes);
}
