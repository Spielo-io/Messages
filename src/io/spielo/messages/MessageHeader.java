package io.spielo.messages;

import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

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
    
    public void intoBuffer(final short length, final BufferBuilder builder) {
    	builder.addShort((short) (length - 2));
    	builder.addShort(senderID).addShort(receiverID);
    	builder.addByte(type1.getByte()).addByte(type2.getByte());
    	builder.addLong(timestamp);
    }
    
    public static MessageHeader parse(BufferIterator iterator) {
    	
    	short senderID = iterator.getNextShort();
    	short receiverID = iterator.getNextShort();
    	iterator.getNext();
    	iterator.getNext();
    	long timestamp = iterator.getNextLong();
    	
    	return new MessageHeader(senderID, receiverID, null, null, timestamp);
    }
}