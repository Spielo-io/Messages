package io.spielo.messages;

import io.spielo.messages.lobbysettings.LobbyBestOf;
import io.spielo.messages.lobbysettings.LobbyGame;
import io.spielo.messages.lobbysettings.LobbyTimer;
import io.spielo.messages.types.GenericEnumMixin;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

import java.nio.charset.StandardCharsets;

public class CreateLobbyMessage extends Message {

	private final Boolean isPublic;
	private final LobbyGame game;
	private final LobbyTimer timer;
	private final LobbyBestOf bestOf;
	private final String username;

	public CreateLobbyMessage(final MessageHeader header, 
			final Boolean isPublic, final LobbyGame game, final LobbyTimer timer, final LobbyBestOf bestOf, final String username) {
		super(header);
		this.isPublic = isPublic;
		this.game = game;
		this.timer = timer;
		this.bestOf = bestOf;
		this.username = username;
	}

	@Override
	protected short getBodyLength() {
		return (short) (4 + username.getBytes(StandardCharsets.UTF_8).length);
	}

	@Override
	protected void bodyIntoBuffer(BufferBuilder builder) {
		builder.addByte((byte)(isPublic ? 1 : 0));
		builder.addByte(game.getByte());
		builder.addByte(timer.getByte());
		builder.addByte(bestOf.getByte());
		builder.addString(username);
	}
	
	public static Message parse(BufferIterator iterator) {	
		MessageHeader header = MessageHeader.parse(iterator);
		Boolean isPublic = iterator.getNext() == 1 ? true : false;
		GenericEnumMixin game = getTypeFromByte(LobbyGame.class, iterator.getNext());
		GenericEnumMixin timer = getTypeFromByte(LobbyTimer.class, iterator.getNext());
		GenericEnumMixin bestOf = getTypeFromByte(LobbyBestOf.class, iterator.getNext());
		String displayName = iterator.getString();
		
		return new CreateLobbyMessage(header, 
				isPublic, (LobbyGame) game, (LobbyTimer) timer, (LobbyBestOf) bestOf, displayName);
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

	public String getUsername() {
		return username;
	}
    
    private static<T extends Enum<T> & GenericEnumMixin> T getTypeFromByte(final Class<T> enumClass, final byte b){
        T type = null;
        for (T a : enumClass.getEnumConstants()) {
            if (a.getByte() == b) {
                type = a;
                break;
            }
        }
        if (type == null) {
            throw new NullPointerException();
        }
        return type;
    }
}
