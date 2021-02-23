package io.spielo.messages.lobbysettings;

import io.spielo.messages.types.GenericEnumMixin;

public enum LobbyGame implements GenericEnumMixin{
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
