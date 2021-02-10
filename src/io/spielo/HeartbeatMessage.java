package io.spielo;

import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2Server;

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
	protected final void bodyIntoBuffer(final byte[] buffer) {
	}
	
	public static HeartbeatMessage parse(final byte[] buffer) {
		return new HeartbeatMessage(MessageHeader.parse(buffer));
	}
}
