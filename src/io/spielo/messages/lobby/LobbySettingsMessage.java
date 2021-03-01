package io.spielo.messages.lobby;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.lobbysettings.LobbySettings;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class LobbySettingsMessage extends Message {

	final LobbySettings settings;
	
	public LobbySettingsMessage(final MessageHeader header, final LobbySettings settings) {
		super(header);
		this.settings = settings;
	}

	public final LobbySettings getSettings() {
		return settings;
	}
	
	@Override
	public
	final short getBodyLength() {
		return settings.getBufferLength();
	}

	@Override
	public
	final void bodyIntoBuffer(BufferBuilder builder) {
		settings.intoBuffer(builder);
	}
	
	public static Message parse(BufferIterator iterator, MessageHeader header) {
		final LobbySettings settings = LobbySettings.parse(iterator, header);
		
		return new LobbySettingsMessage(header, settings);
	}
}
