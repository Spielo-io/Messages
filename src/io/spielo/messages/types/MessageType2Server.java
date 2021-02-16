package io.spielo.messages.types;

public enum MessageType2Server implements GenericEnumMixin, MessageType2{
    CONNECT((byte) 0), 
    HEARTBEAT((byte) 1), 
    DISCONNECT((byte) 2);

    private final byte b;

    MessageType2Server(final byte b) {
        this.b = b;
    }

    public final byte getByte() {
        return b;
    }

}
