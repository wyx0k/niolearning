package indi.wyx0k.entity;

public abstract class Packie {
    private Byte version = 1;
    public abstract Byte getCommand();

    public void setVersion(Byte version) {
        this.version = version;
    }

    public Byte getVersion() {
        return version;
    }
}
