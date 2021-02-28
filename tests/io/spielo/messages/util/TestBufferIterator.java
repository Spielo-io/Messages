package io.spielo.messages.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBufferIterator_Byte {

	byte[] buffer;
	BufferIterator iterator;
	
	@BeforeEach
	void init() {
		buffer = new byte[] { 
			(byte) 0x47
		};
		iterator = new BufferIterator(buffer);
	}
	
	@Test
	void test_hasNext() {
		assertTrue(iterator.hasNext());
	}

	@Test
	void test_hasNext_false() {
		iterator.getNext();
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_getNext() {
		byte b = iterator.getNext();
		assertEquals((byte) 0x47, b);
	}
}
