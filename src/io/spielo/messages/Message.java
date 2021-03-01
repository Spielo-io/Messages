package io.spielo.messages;

import io.spielo.messages.util.BufferBuilder;

public abstract class Message {
	
    private final MessageHeader header;

    protected Message(final MessageHeader header) {
    	this.header = header;
    }

    public byte[] toByteArray() {
    	final short length = (short) (getBodyLength() + MessageHeader.LENGTH);
    	
    	BufferBuilder builder = new BufferBuilder(length);
    	
    	header.intoBuffer(length, builder);
    	bodyIntoBuffer(builder);
    	
    	return builder.build();
    }
    
    public final MessageHeader getHeader() {
    	return header;
    }
    
    public abstract short getBodyLength();
    
    public abstract void bodyIntoBuffer(final BufferBuilder builder);
}
