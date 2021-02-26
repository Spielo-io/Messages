package io.spielo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.spielo.messages.MessageHeader;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Server;

class MessageHeaderTest {

	long time;
	MessageHeader header;
	
	@BeforeEach
	void init() {
		time = System.currentTimeMillis();
		header = new MessageHeader(219, 23489, MessageType1.SERVER, MessageType2Server.HEARTBEAT, time);
	}
	
	@Test
	void test_senderID() {
		assertEquals(219, header.getSenderID());
	}

	@Test
	void test_receiverID() {
		assertEquals(23489, header.getReceiverID());
	}

	@Test
	void test_messageType1() {
		assertEquals(MessageType1.SERVER, header.getType1());
	}

	@Test
	void test_messageType2() {
		assertEquals(MessageType2Server.HEARTBEAT, header.getType2());
	}
	
	@Test
	void test_timestamp() {
		assertEquals(time, header.getTimestamp());
	}
}
