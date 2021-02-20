package io.spielo.util;

public class BufferIterator {
	int iterator;
	final byte[] buffer;
	
	public BufferIterator(final byte[] buffer) {
		this.buffer = buffer;
		iterator = 0;
	}
	
	public final Boolean hasNext() {
		return iterator < buffer.length;
	}
	
	public final Boolean hasNextShort() {
		return iterator + 1 < buffer.length;
	}

	public final Boolean hasNextInt() {
		return iterator + 3 < buffer.length;
	}

	public final Boolean hasNextLong() {
		return iterator + 7 < buffer.length;
	}

	public final Boolean hasNextString() { return iterator + 1 < buffer.length; }

	public final byte getNext() {
		return buffer[iterator++];
	}
	
	public final short getNextShort() {
		return (short) (
				(buffer[iterator++] & 0xFF) | 
				(buffer[iterator++] & 0xFF << 8));
	}
	
	public final int getNextInt() {
		return (int) (
				(buffer[iterator++] & 0xFF) | 
				(buffer[iterator++] & 0xFF << 8)  | 
				(buffer[iterator++] & 0xFF << 16) | 
				(buffer[iterator++] & 0xFF << 24));
	}
	
	public final long getNextLong() {
		long value = 0;
		for (int shift = 0; shift < 64; shift += 8) {
			value |= ((long)(buffer[iterator++] & 0xFF) << shift);
		}
		return value;
	}

	public final String getString() {
		StringBuilder builder = new StringBuilder();
		
		byte b = buffer[iterator];
		do {
			builder.append(b);
			b = buffer[iterator];
		} while (b != '\0');

		return builder.toString();
	}
}
