package io.spielo.util;

public class BufferHelper {

	public static void shortIntoBuffer(final byte[] buffer, final int offset, final int value)
	{
		buffer[offset] = (byte)value;
		buffer[offset + 1] = (byte)(value >> 8);
	}
	
	public static void shortIntoBuffer(final byte[] buffer, final int offset, final short value)
	{
		buffer[offset] = (byte)value;
		buffer[offset + 1] = (byte)(value >> 8);
	}
	
	public static void intIntoBuffer(final byte[] buffer, final int offset, final short value)
	{
		buffer[offset] = (byte)value;
		buffer[offset + 1] = (byte)(value >> 8);
		buffer[offset + 2] = (byte)(value >> 16);
		buffer[offset + 3] = (byte)(value >> 24);
	}
	
	public static void longIntoBuffer(final byte[] buffer, final int offset, final long value) {
		int shift = 0;
		for (int i = 0; i < 8; i++) {
			buffer[offset + i] = (byte)(value >> shift);
			shift += 8;
		}
	}
	
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
}
