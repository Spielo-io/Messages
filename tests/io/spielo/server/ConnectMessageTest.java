package io.spielo.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.spielo.messages.MessageHeader;
import io.spielo.messages.server.ConnectMessage;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Server;

public class ConnectMessageTest {

	ConnectMessage message;
	
	@BeforeEach
	void init() {
		MessageHeader header = new MessageHeader(5, 12, MessageType1.SERVER, MessageType2Server.CONNECT, System.currentTimeMillis());
		message = new ConnectMessage(header);
	}
	
	@Test
	void test_bodyLength() {
		assertEquals(0, message.getBodyLength());
	}
}