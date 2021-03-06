package io.spielo.messages;

import io.spielo.messages.types.ByteEnum;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Game;
import io.spielo.messages.types.MessageType2Lobby;
import io.spielo.messages.types.MessageType2Server;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class MessageHeader {
	public final static short LENGTH = 16;
	
	private final short senderID;
    private final short receiverID;
    private final MessageType1 type1;
    private final ByteEnum type2;
    private final long timestamp;

    public MessageHeader(final short senderID, final short receiverID, final MessageType1 type1, final ByteEnum type2, final long timestamp) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.type1 = type1;
        this.type2 = type2;
        this.timestamp = timestamp;	
    }
    
    public MessageHeader(final int senderID, final int receiverID, final MessageType1 type1, final ByteEnum type2, final long timestamp) {
    	this((short) senderID, (short) receiverID, type1, type2, timestamp);
    }

    public final short getSenderID() {
        return senderID;
    }

    public final short getReceiverID() {
        return receiverID;
    }
    
    public final MessageType1 getType1() {
    	return type1;
    }
    
    public final ByteEnum getType2() {
    	return type2;
    }

    public final long getTimestamp() {
        return timestamp;
    }
    
    public void intoBuffer(final short length, final BufferBuilder builder) {
    	builder.addShort((short) (length - 2));
    	builder.addShort(senderID);
    	builder.addShort(receiverID);
    	builder.addByteEnum(type1);
    	builder.addByteEnum(type2);
    	builder.addLong(timestamp);
    }
    
    public static MessageHeader parse(BufferIterator iterator) {
    	
    	short senderID = iterator.getNextShort();
    	short receiverID = iterator.getNextShort();
    	MessageType1 type1 = iterator.getNextByteEnum(MessageType1.class);
    	ByteEnum type2 = null;
    	
    	switch (type1) {
		case SERVER: 
			type2 = iterator.getNextByteEnum(MessageType2Server.class);
			break;
		case LOBBY: 
			type2 = iterator.getNextByteEnum(MessageType2Lobby.class);
			break;
		case GAME: 
			type2 = iterator.getNextByteEnum(MessageType2Game.class);
			break;
		}
		
    	long timestamp = iterator.getNextLong();
    	
    	return new MessageHeader(senderID, receiverID, type1, type2, timestamp);
    }
}