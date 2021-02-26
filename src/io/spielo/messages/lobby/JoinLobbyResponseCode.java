package io.spielo.messages.lobby;

import io.spielo.messages.types.ByteEnum;

public enum JoinLobbyResponseCode implements ByteEnum {
	Failed(0),
	Success(1),
	OtherPlayerJoined(2);

	final byte value;
	
	private JoinLobbyResponseCode(int value) {
		this.value = (byte) value;
	}
	
	@Override
	public byte getByte() {
		return value;
	}	
}
