package io.spielo.messages.lobby;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class LobbyListRequestMessage extends Message {

	public LobbyListRequestMessage(final MessageHeader header) {
		super(header);
	}

	@Override
	public short getBodyLength() {
		return 0;
	}

	@Override
	public void bodyIntoBuffer(BufferBuilder builder) {
	}

	public static Message parse(final BufferIterator iterator, final MessageHeader header) {
		return new LobbyListRequestMessage(header);
	}
}
