package io.spielo.messages.server;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
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
	public short getBodyLength() {
		return 0;
	}

	@Override
	public void bodyIntoBuffer(final BufferBuilder builder) {		
	}

	public static Message parse(final BufferIterator iterator, final MessageHeader header) {
		return new ConnectMessage(header);
	}
}