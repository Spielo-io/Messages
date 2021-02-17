package io.spielo.util;

import java.util.Arrays;

public class BufferHelper {

	public static short fromBufferIntoShort(final byte[] buffer, final int offset) {
		return (short)(buffer[offset] & 0xFF | buffer[offset + 1] & 0xFF << 8);
	}

	public static int fromBufferIntoInt(final byte[] buffer, final int offset) {
		return buffer[offset] & 0xFF | 
			(buffer[offset + 1] & 0xFF <<  8) |  
			(buffer[offset + 2] & 0xFF << 16) | 
			(buffer[offset + 3] & 0xFF << 24); 
	}

	public static long fromBufferIntoLong(final byte[] buffer, final int offset) {
		long value = 0;
		int shift = 0;
		for (int i = 0; i < 8; i++) {
			value |= ((long)(buffer[offset + i] & 0xFF) << shift);
			shift += 8;
		}
		return value;
	}

	public static String getChar(final byte[] buffer, int offset) {
		byte[] newArray = Arrays.copyOfRange(buffer, offset, offset+2);
		return new String(newArray);
	}
}
