package io.spielo.messages.types;

public enum MessageType2Lobby implements GenericEnumMixin, MessageType2{
    CREATE((byte) 0), 
    SETTINGS((byte) 1), 
    JOIN((byte) 2);

    private final byte b;

    MessageType2Lobby(final byte b) {
        this.b = b;
    }

    public final byte getByte() {
        return b;
    }
}
