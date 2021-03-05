package io.spielo.messages.lobbysettings;

import io.spielo.messages.MessageHeader;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class LobbySettings {

	private final Boolean isPublic;
	private final LobbyGame game;
	private final LobbyTimer timer;
	private final LobbyBestOf bestOf;
	
	public LobbySettings(final Boolean isPublic, final LobbyGame game, final LobbyTimer timer, final LobbyBestOf bestOf) {
		this.isPublic = isPublic;
		this.game = game;
		this.timer = timer;
		this.bestOf = bestOf;
	}
	
	public Boolean getPublic() {
		return isPublic;
	}

	public LobbyGame getGame() {
		return game;
	}

	public LobbyTimer getTimer() {
		return timer;
	}

	public LobbyBestOf getBestOf() {
		return bestOf;
	}
	
	public final short getBufferLength() {
		return 4;
	}

	public final void intoBuffer(final BufferBuilder builder) {
		builder.addByte((byte)(isPublic ? 1 : 0));
		builder.addByte(game.getByte());
		builder.addByte(timer.getByte());
		builder.addByte(bestOf.getByte());
	}

	public static LobbySettings parse(final BufferIterator iterator, final MessageHeader header) {
		Boolean isPublic = iterator.getNext() == 1 ? true : false;
		LobbyGame game = iterator.getNextByteEnum(LobbyGame.class);
		LobbyTimer timer = iterator.getNextByteEnum(LobbyTimer.class);
		LobbyBestOf bestOf = iterator.getNextByteEnum(LobbyBestOf.class);
		
		return new LobbySettings(isPublic, game, timer, bestOf);
	}
}
