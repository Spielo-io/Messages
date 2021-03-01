package io.spielo.messages.lobby;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class ReadyToPlayMessage extends Message {

	private final Boolean isReady;
	
	public ReadyToPlayMessage(final MessageHeader header, final Boolean isReady) {
		super(header);
		this.isReady = isReady;
	}

	@Override
	public short getBodyLength() {
		return 1;
	}

	@Override
	public void bodyIntoBuffer(BufferBuilder builder) {
		builder.addBool(isReady);
	}
	
	public static Message parse(final BufferIterator iterator, final MessageHeader header) {
		Boolean isReady = iterator.getNextBool();
		return new ReadyToPlayMessage(header, isReady);
	}
}
