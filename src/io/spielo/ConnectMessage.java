package io.spielo;

import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2Server;

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
	protected void bodyIntoBuffer(byte[] buffer) {		
	}

	public static Message parse(byte[] bytes) {	
		MessageHeader header = MessageHeader.parse(bytes);
		return new ConnectMessage(header);
	}
}