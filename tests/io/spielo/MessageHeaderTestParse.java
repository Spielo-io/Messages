package io.spielo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.spielo.messages.MessageHeader;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Server;
import io.spielo.messages.util.BufferIterator;

class MessageHeaderTestParse {

	byte[] buffer;
	BufferIterator iterator;
	MessageHeader header;
	
	@BeforeEach
	void init() {
		buffer = new byte[] {
			//SenderID = -20.635
			(byte) 0x65,
			(byte) 0xAF,	
			//ReceiverID = 31.071
			(byte) 0x5F,	
			(byte) 0x79,	
			//MessageType1 = SERVER
			MessageType1.SERVER.getByte(),	
			//MessageType2 = Heartbeat
			MessageType2Server.HEARTBEAT.getByte(),	
			//Time: 195469974773066327
			(byte) 0x57,
			(byte) 0x6A,
			(byte) 0x9A,
			(byte) 0x0A,
			(byte) 0xE8,
			(byte) 0x72,
			(byte) 0xB6,
			(byte) 0x02,
		};
		
		iterator = new BufferIterator(buffer);
		header = MessageHeader.parse(iterator);
	}
	
	@Test
	void test_senderID() {
		assertEquals(-20635, header.getSenderID());
	}

	@Test
	void test_receiverID() {
		assertEquals(31071, header.getReceiverID());
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
		assertEquals(195469974773066327L, header.getTimestamp());
	}
}
