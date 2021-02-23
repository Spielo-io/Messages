package io.spielo.messages.util;

import org.junit.jupiter.api.Test;

import io.spielo.messages.util.BufferBuilder;

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
		short[] valuesToTest = new short[] { 
				Short.MAX_VALUE, 
				Short.MIN_VALUE };
		byte[] bytesToCompare = new byte[] { 
				-1, 127,         
				0, -128 };
		
		BufferBuilder builder = new BufferBuilder(valuesToTest.length * 2);
		for (short value : valuesToTest) {
			builder.addShort(value);
		}

		assertEquals(bytesToCompare.length, builder.buffer.size());
		for (int i = 0; i < builder.buffer.size(); i++) {
			assertEquals(bytesToCompare[i], builder.buffer.get(i).byteValue());
		}
	}

	@Test
	void testAddInt() {
		int[] valuesToTest = new int[] {     
				Integer.MAX_VALUE, 
				Integer.MIN_VALUE };
		byte[] bytesToCompare = new byte[] { 
				-1, -1, -1, 127,   
				0, 0, 0, -128};
		
		BufferBuilder builder = new BufferBuilder(valuesToTest.length * 4);
		for (int value : valuesToTest) {
			builder.addInt(value);
		}

		assertEquals(bytesToCompare.length, builder.buffer.size());
		for (int i = 0; i < builder.buffer.size(); i++) {
			assertEquals(bytesToCompare[i], builder.buffer.get(i).byteValue());
		}
	}

	@Test
	void testAddLong() {
		long[] valuesToTest = new long[] {     
				Long.MAX_VALUE, 
				Long.MIN_VALUE };
		byte[] bytesToCompare = new byte[] { 
				-1, -1, -1, -1, -1, -1, -1, 127,   
				0, 0, 0, 0, 0, 0, 0, -128};
		
		BufferBuilder builder = new BufferBuilder(valuesToTest.length * 8);
		for (long value : valuesToTest) {
			builder.addLong(value);
		}

		assertEquals(bytesToCompare.length, builder.buffer.size());
		for (int i = 0; i < builder.buffer.size(); i++) {
			assertEquals(bytesToCompare[i], builder.buffer.get(i).byteValue());
		}
	}

	@Test
	void testAddString() {
		String value = "Hello World! This is a test.";
		
		BufferBuilder builder = new BufferBuilder(value.length());
		builder.addString(value);

		assertEquals(value.length() + 1, builder.buffer.size());
		for (int i = 0; i < value.length(); i++) {
			assertEquals(value.getBytes()[i], builder.buffer.get(i).byteValue());
		}
		assertEquals(builder.buffer.get(builder.buffer.size() - 1), (byte) '\0');
	}
}
