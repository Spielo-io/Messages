package io.spielo.util;

import java.util.ArrayList;
import java.util.List;

public class BufferBuilder {
	final List<Byte> buffer;
	
	public BufferBuilder() {
		buffer = new ArrayList<>();
	}
	
	public BufferBuilder(final int initialCapacity) {
		buffer = new ArrayList<>(initialCapacity);
	}
	
	public final BufferBuilder addByte(final byte value) {
		buffer.add(value);
		return this;
	}

	public final BufferBuilder addShort(final short value) {
		buffer.add((byte)value);
		buffer.add((byte)(value >> 8));
		return this;
	}

	public final BufferBuilder addInt(final int value) {
		addShort((short)value);
		addShort((short)(value >> 16));
		return this;
	}

	public final BufferBuilder addLong(final long value) {
		addInt((int)value);
		addInt((int)(value >> 32));
		return this;
	}
	
	public final BufferBuilder addString(final String value) {
		for (Byte b : value.getBytes()) {
			buffer.add(b);
		}
		return this;
	}
	
	public final byte[] build() {
		byte[] array = new byte[buffer.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = buffer.get(i);
		}
		return array;
	}
}
