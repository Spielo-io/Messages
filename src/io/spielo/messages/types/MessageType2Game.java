package io.spielo.messages.types;

public enum MessageType2Game implements ByteEnum {
    TicTacToe(0),
    Win4(1),
    Mill(2),
    Checkers(3);
	
    private final byte b;

    MessageType2Game(final int b) {
        this.b = (byte) b;
    }

    @Override
    public final byte getByte() {
        return b;
    }
}
