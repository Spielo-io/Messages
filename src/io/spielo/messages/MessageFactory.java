package io.spielo.messages;

import io.spielo.messages.types.GenericEnumMixin;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Lobby;
import io.spielo.messages.types.MessageType2Server;
import io.spielo.messages.util.BufferIterator;

public class MessageFactory {
    public Message getMessage(final byte[] bytes) {

        MessageType1 type1 = getTypeFromByte(MessageType1.class, bytes, 4);
        switch (type1) {
        	case SERVER:
        		return getServerMessage(bytes);
            case LOBBY:
            	return getLobbyMessage(bytes);
            case GAME:
                return getGameMessage(bytes);
            default:
                return null;
        }
    }

    private Message getServerMessage(final byte[] bytes) {
    	BufferIterator iterator = new BufferIterator(bytes);
        MessageType2Server type2 = getTypeFromByte(MessageType2Server.class, bytes, 5);
        switch (type2) {
			case CONNECT: 
				return ConnectMessage.parse(iterator);
			case HEARTBEAT:
				return HeartbeatMessage.parse(iterator);
			case DISCONNECT:
				break;
		}
    	return null;
    }
    
    private Message getLobbyMessage(final byte[] bytes) {
    	BufferIterator iterator = new BufferIterator(bytes);
        MessageType2Lobby type2 = getTypeFromByte(MessageType2Lobby.class, bytes, 5);
        switch (type2) {
            case CREATE:
				return CreateLobbyMessage.parse(iterator);
            case SETTINGS:
            	break;
            case JOIN:
                return JoinLobbyMessage.parse(iterator);
            case CREATERESPONSE:
                return CreateLobbyResponseMessage.parse(iterator);
            case JOINRESPONSE:
                return JoinLobbyResponseMessage.parse(iterator);
            case SETTINGSRESPONSE:
                break;
            default:
				break;
        }
        return null;
    }
    
    private Message getGameMessage(final byte[] bytes) {
    	return null;
    }
    
    private static<T extends Enum<T> & GenericEnumMixin> T getTypeFromByte(final Class<T> enumClass, final byte[] bytes, final int offset){
        T type = null;
        for (T a : enumClass.getEnumConstants()) {
            if (a.getByte() == bytes[offset]) {
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
