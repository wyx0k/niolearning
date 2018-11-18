package indi.wyx0k.entity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static indi.wyx0k.entity.Command.LOGIN_REQUEST;

public class packieCodec {
    public static final int MAGICA = 0x12345678;
    private static final Map<Byte,Class<? extends Packie>> packieMap;
    private static final Map<Byte,Serializer> serializerMap;
    static{
        packieMap = new HashMap<Byte,Class<? extends Packie>>();
        serializerMap = new HashMap<Byte, Serializer>();
        packieMap.put(LOGIN_REQUEST,LoginRequestPackie.class);
        serializerMap.put(Serializer.DEFAULT.getSerializerAlgorithm(),Serializer.DEFAULT);
    }
    public ByteBuf encode(Packie pack){
        byte[] bytes = Serializer.DEFAULT.serialize(pack);
        ByteBuf bf = ByteBufAllocator.DEFAULT.ioBuffer();
        bf.writeInt(MAGICA);
        bf.writeByte(pack.getVersion());
        bf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        bf.writeByte(pack.getCommand());
        bf.writeInt(bytes.length);
        bf.writeBytes(bytes);
        return bf;
    }
    public Packie decode(ByteBuf bf){
        bf.skipBytes(5);
        Byte algo = bf.readByte();
        Byte command = bf.readByte();
        Integer pLength = bf.readInt();
        byte[] p = new byte[pLength];
        bf.readBytes(p);
        Class<? extends Packie> req = getRequestType(command);
        Serializer serializer = getSerializer(algo);
        if(req != null && serializer != null){
            return serializer.deserialize(req,p);
        }
        return null;
    }
    private Serializer getSerializer(byte serializerCode){
        return serializerMap.get(serializerCode);
    }
    private  Class<? extends Packie> getRequestType(byte commandCode){
        return packieMap.get(commandCode);
    }
}