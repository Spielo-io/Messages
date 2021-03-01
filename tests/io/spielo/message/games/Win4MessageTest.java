package io.spielo.message.games;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.spielo.messages.MessageHeader;
import io.spielo.messages.games.Win4Message;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Server;

class Win4MessageTest {

	Win4Message message;
	
	@BeforeEach
	void init() {
		MessageHeader header = new MessageHeader(5, 12, MessageType1.SERVER, MessageType2Server.CONNECT, System.currentTimeMillis());
		message = new Win4Message(header, (byte) 5);
	}
	
	@Test
	void test_value() {
		assertEquals(5, message.getValue());
	}
	
	@Test
	void test_bodyLength() {
		assertEquals(1, message.getBodyLength());
	}
}