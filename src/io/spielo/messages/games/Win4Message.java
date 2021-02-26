package io.spielo.messages.games;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class Win4Message extends Message {
	private final byte value;
	
    public Win4Message(final MessageHeader header, final byte value) {
    	super(header);
    	
    	this.value = value;
	}
    
    @Override
    protected short getBodyLength() {
    	return 1;
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
    	builder.addByte(value);
    }

    public static Message parse(BufferIterator iterator, final MessageHeader header) {
    	byte value = iterator.getNext();
    	return new TicTacToeMessage(header, value);
    }

    public final byte getValue() {
        return value;
    }
}