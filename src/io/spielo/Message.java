package io.spielo;

public abstract class Message {
	
    private final MessageHeader header;

    protected Message(final MessageHeader header) {
    	this.header = header;
    }

    public byte[] toByteArray() {
    	final short length = (short) (getBodyLength() + MessageHeader.LENGTH);
    	
    	final byte[] buffer = new byte[length];
    	
    	header.intoBuffer(buffer);
    	bodyIntoBuffer(buffer);
    	
    	return buffer;
    }
    
    public final MessageHeader getHeader() {
    	return header;
    }
    
    protected abstract short getBodyLength();
    
    protected abstract void bodyIntoBuffer(final byte[] buffer);
}
