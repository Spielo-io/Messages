package io.spielo.messages;

import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Server;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class ConnectMessage extends Message {

	public ConnectMessage(final MessageHeader header) {
		super(header);
	}

	public ConnectMessage(final long timestamp) {
		super(new MessageHeader(0, 0, MessageType1.SERVER, MessageType2Server.CONNECT, timestamp));
	}
	
	public ConnectMessage(final short receiverID, final long timestamp) {
		super(new MessageHeader(0, receiverID, MessageType1.SERVER, MessageType2Server.CONNECT, timestamp));
	}
	
	@Override
	protected short getBodyLength() {
		return 0;
	}

	@Override
	protected void bodyIntoBuffer(final BufferBuilder builder) {		
	}

	public static Message parse(BufferIterator iterator) {	
		MessageHeader header = MessageHeader.parse(iterator);
		return new ConnectMessage(header);
	}
}