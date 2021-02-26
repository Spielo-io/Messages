package io.spielo.messages.types;

public enum MessageType1 implements ByteEnum{
    LOBBY((byte) 0), SERVER((byte) 1), GAME((byte) 2);

    private final byte b;

    MessageType1(byte b) {
        this.b = b;
    }

    @Override
    public byte getByte() {
        return b;
    }

}
