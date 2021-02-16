package io.spielo;

import io.spielo.util.BufferBuilder;
import io.spielo.util.BufferIterator;

public class CreateLobbyMessage extends Message {

	private final Boolean isPublic;
	private final byte game;
	private final byte timer;
	private final byte bestOf;
	
	protected CreateLobbyMessage(final MessageHeader header) {
		this(header, false, (byte) 0, (byte) 0, (byte) 0);
	}
	
	protected CreateLobbyMessage(final MessageHeader header, final Boolean isPublic, final byte game, final byte timer, final byte bestOf) {
		super(header);
		this.isPublic = isPublic;
		this.game = game;
		this.timer = timer;
		this.bestOf = bestOf;
	}

	@Override
	protected short getBodyLength() {
		return 4;
	}

	@Override
	protected void bodyIntoBuffer(BufferBuilder builder) {
		builder.addByte((byte)(isPublic ? 1 : 0));
		builder.addByte(game);
		builder.addByte(timer);
		builder.addByte(bestOf);
	}
	
	public static Message parse(BufferIterator iterator) {	
		MessageHeader header = MessageHeader.parse(iterator);
		Boolean isPublic = iterator.getNext() == 1 ? true : false;
		byte game = iterator.getNext();
		byte timer = iterator.getNext();
		byte bestOf = iterator.getNext();
		
		return new CreateLobbyMessage(header, isPublic, game, timer, bestOf);
	}
}
