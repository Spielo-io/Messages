package io.spielo;

import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2Lobby;

public class LobbyCreateMessage extends Message {

	protected LobbyCreateMessage(short senderID, short receiverID, long timestamp) {
		super(senderID, receiverID, MessageType1.LOBBY, MessageType2Lobby.CREATE, timestamp);
	}

	@Override
	protected short getBodyLength() {
		return 0;
	}

	@Override
	protected void bodyIntoBuffer(byte[] buffer) {
		
	}
	
	public static Message parse(byte[] buffer) {
		return null;
	}
}
