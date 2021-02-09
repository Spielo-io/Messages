package io.spielo;

import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2;
import io.spielo.util.BufferHelper;

public class MessageHeader {
	public final static short LENGTH = 16;
	
	private final short senderID;
    private final short receiverID;
    private final MessageType1 type1;
    private final MessageType2 type2;
    private final long timestamp;

    public MessageHeader(final short senderID, final short receiverID, final MessageType1 type1, final MessageType2 type2, final long timestamp) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.type1 = type1;
        this.type2 = type2;
        this.timestamp = timestamp;	
    }
    
    public MessageHeader(final int senderID, final int receiverID, final MessageType1 type1, final MessageType2 type2, final long timestamp) {
    	this((short) senderID, (short) receiverID, type1, type2, timestamp);
    }

    public final short getSenderID() {
        return senderID;
    }

    public final short getReceiverID() {
        return receiverID;
    }

    public final long getTimestamp() {
        return timestamp;
    }
    
    public void intoBuffer(byte[] buffer) {
    	BufferHelper.shortIntoBuffer(buffer, 0, buffer.length - 2);
    	BufferHelper.shortIntoBuffer(buffer, 2, senderID);
    	BufferHelper.shortIntoBuffer(buffer, 4, receiverID);
    	buffer[6] = type1.getByte();
    	buffer[7] = type2.getByte();
    	BufferHelper.longIntoBuffer(buffer, 8, timestamp);
    }
    
    public static MessageHeader parse(byte[] buffer) {
    	short senderID = BufferHelper.fromBufferIntoShort(buffer, 0);
    	short receiverID = BufferHelper.fromBufferIntoShort(buffer, 2);
    	long timestamp = BufferHelper.fromBufferIntoLong(buffer, 6);
    	
    	return new MessageHeader(senderID, receiverID, null, null, timestamp);
    }
}