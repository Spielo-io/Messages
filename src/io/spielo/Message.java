package io.spielo;

import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2;
import io.spielo.util.BufferHelper;

public abstract class Message {
    protected final static short HEADER_LENGTH = 16;
	
	private final short senderID;
    private final short receiverID;
    private final MessageType1 type1;
    private final MessageType2 type2;
    private final long timestamp;

    protected Message(final short senderID, final short receiverID, final MessageType1 type1, final MessageType2 type2, final long timestamp) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.type1 = type1;
        this.type2 = type2;
        this.timestamp = timestamp;
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
    
    public byte[] toByteArray() {
    	final short length = (short) (getBodyLength() + HEADER_LENGTH);
    	
    	final byte[] buffer = new byte[length];
    	
    	headerIntoBuffer(buffer, length);
    	bodyIntoBuffer(buffer);
    	
    	return buffer;
    }
    
    protected abstract short getBodyLength();
    
    protected abstract void bodyIntoBuffer(final byte[] buffer);

    private void headerIntoBuffer(final byte[] buffer, final short length) {
    	BufferHelper.shortIntoBuffer(buffer, 0, length - 2);
    	BufferHelper.shortIntoBuffer(buffer, 2, senderID);
    	BufferHelper.shortIntoBuffer(buffer, 4, receiverID);
    	buffer[6] = type1.getByte();
    	buffer[7] = type2.getByte();
    	BufferHelper.longIntoBuffer(buffer, 8, timestamp);
    }
}
