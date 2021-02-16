package io.spielo;

import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2Server;
import io.spielo.util.BufferBuilder;
import io.spielo.util.BufferIterator;

public class HeartbeatMessage extends Message {
	
	public HeartbeatMessage(final MessageHeader header) {
		super(header);
	}

	public HeartbeatMessage(final int senderID, final long timestamp) {
		super(new MessageHeader(senderID, 0, MessageType1.SERVER, MessageType2Server.HEARTBEAT, timestamp));
	}

	@Override
	protected final short getBodyLength() {
		return 0;
	}

	@Override
	protected final void bodyIntoBuffer(final BufferBuilder builder) {
	}
	
	public static HeartbeatMessage parse(BufferIterator iterator) {
		return new HeartbeatMessage(MessageHeader.parse(iterator));
	}
}
