package io.spielo.messages.lobbysettings;

import io.spielo.messages.types.GenericEnumMixin;

public enum LobbyBestOf implements GenericEnumMixin{
	UNKNOWN(-1),
	BestOf_1(0),
	BestOf_3(1),
	BestOf_5(2),
	BestOf_7(3),
	BestOf_9(4),
	Indefinite(3);

	private final byte value;
	
	LobbyBestOf(final int value) {
		this.value = (byte) value;
	}
	
	@Override
	public byte getByte() {
		return value;
	}
}