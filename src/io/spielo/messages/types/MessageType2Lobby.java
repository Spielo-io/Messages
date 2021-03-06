package io.spielo.messages.types;

public enum MessageType2Lobby implements ByteEnum {
    CREATE((byte) 0), 
    SETTINGS((byte) 1), 
    JOIN((byte) 2),
    JOINRESPONSE((byte) 3),
    CREATERESPONSE((byte) 4),
    SETTINGSRESPONSE((byte) 5), 
    LOBBY_LIST((byte) 6), 
    LOBBY_LIST_REQUEST((byte) 7), 
    LOBBY_IS_READY((byte) 8),
    LEAVE((byte) 9);

    private final byte b;

    MessageType2Lobby(final byte b) {
        this.b = b;
    }

    public final byte getByte() {
        return b;
    }
}
