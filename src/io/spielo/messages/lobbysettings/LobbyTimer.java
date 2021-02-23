package io.spielo.messages.lobbysettings;

import io.spielo.messages.types.GenericEnumMixin;

public enum LobbyTimer implements GenericEnumMixin{
	UNKNOWN(-1),
	Seconds_30(0),
	Minute_1(1),
	Minute_3(2);

	private final byte value;
	
	LobbyTimer(final int value) {
		this.value = (byte) value;
	}
	
	@Override
	public byte getByte() {
		return value;
	}
}