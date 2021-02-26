package io.spielo.messages.lobbysettings;

import io.spielo.messages.types.ByteEnum;

public enum LobbyGame implements ByteEnum{
	UNKNOWN(-1),
	TicTacToe(0),
	Win4(1),
	Mill(2),
	Checkers(3);

	private final byte value;
	
	LobbyGame(final int value) {
		this.value = (byte) value;
	}
	
	@Override
	public byte getByte() {
		return value;
	}
}
