package io.spielo.messages.types;

public enum MessageType2Game implements GenericEnumMixin, MessageType2{
    TEST((byte) 0);
	
    private final byte b;

    MessageType2Game(final byte b) {
        this.b = b;
    }

    @Override
    public final byte getByte() {
        return b;
    }
}
