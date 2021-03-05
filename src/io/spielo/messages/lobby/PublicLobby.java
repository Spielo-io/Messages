package io.spielo.messages.lobby;

import io.spielo.messages.lobbysettings.LobbySettings;

public class PublicLobby {
	private final LobbySettings settings;
	private final String lobbyCode;
	private final String hostname;
	
	public PublicLobby(final LobbySettings settings, final String lobbyCode, final String hostname) {
		this.settings = settings;
		this.lobbyCode = lobbyCode;
		this.hostname = hostname;
	}
	
	public final LobbySettings getSettings() {
		return settings;
	}
	
	public final String getLobbyCode() {
		return lobbyCode;
	}
	
	public final String getHostname() {
		return hostname;
	}
}