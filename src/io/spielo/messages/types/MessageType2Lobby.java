package io.spielo.messages.types;

public enum MessageType2Lobby implements ByteEnum, MessageType2{
    CREATE((byte) 0), 
    SETTINGS((byte) 1), 
    JOIN((byte) 2),
    JOINRESPONSE((byte) 3),
    CREATERESPONSE((byte) 4),
    SETTINGSRESPONSE((byte) 5);

    private final byte b;

    MessageType2Lobby(final byte b) {
        this.b = b;
    }

    public final byte getByte() {
        return b;
    }
}
