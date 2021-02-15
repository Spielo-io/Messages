package io.spielo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class BufferBuilderTest {

	@Test
	void testAddByte() {
		byte[] bytesToAdd = new byte[] { 2, 56, 21, 6, 127, -128};
		
		BufferBuilder builder = new BufferBuilder(1);
		for (byte b : bytesToAdd) {
			builder.addByte(b);
		}
		
		for (int i = 0; i < bytesToAdd.length; i++) {
			assertEquals(bytesToAdd[i], builder.buffer.get(i));
		}
		assertEquals(bytesToAdd.length, builder.buffer.size());
	}

	@Test
	void testAddShort() {
		fail("Not yet implemented");
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
