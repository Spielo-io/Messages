package io.spielo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BufferBuilderTest {

	@Test
	void testAddByte() {
		byte[] bytesToAdd = new byte[] { 2, 56, 21, 6, 127, -128};
		
		BufferBuilder builder = new BufferBuilder(bytesToAdd.length);
		for (byte b : bytesToAdd) {
			builder.addByte(b);
		}
		
		for (int i = 0; i < bytesToAdd.length; i++) {
			assertEquals(bytesToAdd[i], builder.buffer.get(i).byteValue());
		}
		assertEquals(bytesToAdd.length, builder.buffer.size());
	}

	@Test
	void testAddShort() {
		short s = 1;
		BufferBuilder builder = new BufferBuilder(2);
		builder.addShort(s);
		assertEquals(builder.buffer.get(0), (byte) 1);
		assertEquals(builder.buffer.get(1), (byte) 0);
	}

	@Test
	void testAddInt() {
		fail("Not yet implemented");
	}

	@Test
	void testAddLong() {
		fail("Not yet implemented");
	}

	@Test
	void testAddString() {
		fail("Not yet implemented");
	}

	@Test
	void testBuild() {
		fail("Not yet implemented");
	}

}
