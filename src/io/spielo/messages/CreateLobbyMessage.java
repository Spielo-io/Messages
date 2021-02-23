package io.spielo.messages;

import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

import java.nio.charset.StandardCharsets;

public class CreateLobbyMessage extends Message {

	private final Boolean isPublic;
	private final byte game;
	private final byte timer;
	private final byte bestOf;
	private final String displayName;

	public CreateLobbyMessage(final MessageHeader header, final Boolean isPublic, final byte game, final byte timer, final byte bestOf, final String displayName) {
		super(header);
		this.isPublic = isPublic;
		this.game = game;
		this.timer = timer;
		this.bestOf = bestOf;
		this.displayName = displayName;
	}

	public CreateLobbyMessage(final MessageHeader header, final Boolean isPublic, final byte game, final byte timer, final byte bestOf) {
		super(header);
		this.isPublic = isPublic;
		this.game = game;
		this.timer = timer;
		this.bestOf = bestOf;
		this.displayName = "";
	}
	
	protected CreateLobbyMessage(final MessageHeader header) {
		this(header, false, (byte) 0, (byte) 0, (byte) 0, "");
	}

	@Override
	protected short getBodyLength() {
		return (short) (4 + displayName.getBytes(StandardCharsets.UTF_8).length);
	}

	@Override
	protected void bodyIntoBuffer(BufferBuilder builder) {
		builder.addByte((byte)(isPublic ? 1 : 0));
		builder.addByte(game);
		builder.addByte(timer);
		builder.addByte(bestOf);
		builder.addString(displayName);
	}
	
	public static Message parse(BufferIterator iterator) {	
		MessageHeader header = MessageHeader.parse(iterator);
		Boolean isPublic = iterator.getNext() == 1 ? true : false;
		byte game = iterator.getNext();
		byte timer = iterator.getNext();
		byte bestOf = iterator.getNext();
		String displayName = iterator.getString();
		
		return new CreateLobbyMessage(header, isPublic, game, timer, bestOf, displayName);
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public byte getGame() {
		return game;
	}

	public byte getTimer() {
		return timer;
	}

	public byte getBestOf() {
		return bestOf;
	}

	public String getDisplayName() {
		return displayName;
	}
}
