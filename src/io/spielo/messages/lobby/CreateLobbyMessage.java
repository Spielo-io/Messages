package io.spielo.messages.lobby;

import java.nio.charset.StandardCharsets;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.lobbysettings.LobbySettings;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class CreateLobbyMessage extends Message {

	private final LobbySettings settings;
	private final String username;

	public CreateLobbyMessage(final MessageHeader header, final LobbySettings settings, final String username) {
		super(header);
		this.settings = settings;
		this.username = username;
	}

	public final LobbySettings getLobbySettings() {
		return settings;
	}

	public final String getUsername() {
		return username;
	}
	
	@Override
	protected final short getBodyLength() {
		return (short) (settings.getBufferLength() + username.getBytes(StandardCharsets.UTF_8).length + 1);
	}

	@Override
	protected final void bodyIntoBuffer(BufferBuilder builder) {
		settings.intoBuffer(builder);
		builder.addString(username);
	}
	
	public static Message parse(BufferIterator iterator, MessageHeader header) {	
		LobbySettings settings = LobbySettings.parse(iterator, header);
		String username = iterator.getString();
		
		return new CreateLobbyMessage(header, settings, username);
	}
}
