package io.spielo.messages;

import io.spielo.messages.games.TicTacToeMessage;
import io.spielo.messages.games.Win4Message;
import io.spielo.messages.lobby.CreateLobbyMessage;
import io.spielo.messages.lobby.CreateLobbyResponseMessage;
import io.spielo.messages.lobby.JoinLobbyMessage;
import io.spielo.messages.lobby.JoinLobbyResponseMessage;
import io.spielo.messages.lobby.LobbySettingsMessage;
import io.spielo.messages.lobby.PublicLobbyListMessage;
import io.spielo.messages.server.ConnectMessage;
import io.spielo.messages.server.HeartbeatMessage;
import io.spielo.messages.types.MessageType2Game;
import io.spielo.messages.types.MessageType2Lobby;
import io.spielo.messages.types.MessageType2Server;
import io.spielo.messages.util.BufferIterator;

public class MessageFactory {
    public Message getMessage(final byte[] bytes) {

    	BufferIterator iterator = new BufferIterator(bytes);
    	MessageHeader header = MessageHeader.parse(iterator);
    	
        switch (header.getType1()) {
        	case SERVER:
        		return getServerMessage(iterator, header);
            case LOBBY:
            	return getLobbyMessage(iterator, header);
            case GAME:
                return getGameMessage(iterator, header);
            default:
                return null;
        }
    }

    private Message getServerMessage(final BufferIterator iterator, final MessageHeader header) {
        MessageType2Server type2 = (MessageType2Server) header.getType2();
        switch (type2) {
			case CONNECT: 
				return ConnectMessage.parse(iterator, header);
			case HEARTBEAT:
				return HeartbeatMessage.parse(iterator, header);
			case DISCONNECT:
				break;
		}
    	return null;
    }
    
    private Message getLobbyMessage(final BufferIterator iterator, final MessageHeader header) {
    	MessageType2Lobby type2 = (MessageType2Lobby) header.getType2();
        switch (type2) {
            case CREATE:
				return CreateLobbyMessage.parse(iterator, header);
            case LOBBY_LIST:
            	return PublicLobbyListMessage.parse(iterator, header);
            case SETTINGS:
            	return LobbySettingsMessage.parse(iterator, header);
            case JOIN:
                return JoinLobbyMessage.parse(iterator, header);
            case CREATERESPONSE:
                return CreateLobbyResponseMessage.parse(iterator, header);
            case JOINRESPONSE:
                return JoinLobbyResponseMessage.parse(iterator, header);
            case SETTINGSRESPONSE:
                break;
            default:
				break;
        }
        return null;
    }
    
    private Message getGameMessage(final BufferIterator iterator, final MessageHeader header) {
    	MessageType2Game type2 = (MessageType2Game) header.getType2();
        switch (type2) {
			case TicTacToe:
				return TicTacToeMessage.parse(iterator, header);
			case Win4:
				return Win4Message.parse(iterator, header);
				
		default:
			break;
		}
    	return null;
    }
}
