package io.spielo.messages.lobby;

import java.util.ArrayList;
import java.util.List;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.lobbysettings.LobbySettings;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class PublicLobbyListMessage extends Message {
	
	private short bodyLength;
	private final List<PublicLobby> list;
	
	protected PublicLobbyListMessage(final MessageHeader header, final int estimatedCount) {
		super(header);
		
		bodyLength = 0;
		list = new ArrayList<>(estimatedCount);
	}

	public void addLobby(final LobbySettings settings, final String lobbyCode, final String hostname) {
		PublicLobby lobby = new PublicLobby(settings, lobbyCode, hostname);
		list.add(lobby);
		
		bodyLength += 6 + lobbyCode.length() + hostname.length();
	}
	
	public final List<PublicLobby> getList() {
		return list;
	}
	
	@Override
	protected final short getBodyLength() {
		return bodyLength;
	}

	@Override
	protected final void bodyIntoBuffer(final BufferBuilder builder) {
		for (PublicLobby publicLobby : list) {
			publicLobby.getSettings().intoBuffer(builder);
			builder.addString(publicLobby.getLobbyCode());
			builder.addString(publicLobby.getHostname());
		}
	}
	
	public static Message parse(final BufferIterator iterator, final MessageHeader header) {
		PublicLobbyListMessage listMessage = new PublicLobbyListMessage(header, 0);
		
		while (iterator.hasNext()) {
			LobbySettings settings = LobbySettings.parse(iterator, header);
			String lobbyCode = iterator.getString();
			String hostname = iterator.getString();
			
			listMessage.addLobby(settings, lobbyCode, hostname);
		}
		
		return listMessage;
	}
}
