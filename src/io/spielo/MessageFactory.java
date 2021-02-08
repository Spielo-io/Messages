package io.spielo;

import io.spielo.types.GenericEnumMixin;
import io.spielo.types.MessageType1;
import io.spielo.types.MessageType2Game;
import io.spielo.types.MessageType2Lobby;
import io.spielo.types.MessageType2Server;
import io.spielo.util.*;

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
    	return null;
    }
    
    private Message getLobbyMessage(final byte[] bytes) {
        MessageType2Lobby type2 = getTypeFromByte(MessageType2Lobby.class, bytes, 5);
        switch (type2) {
            case CREATE:
            	return LobbyCreateMessage.parse(bytes);
            case SETTINGS:
            	break;
            case JOIN:
                break;
            default:
                return null;
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
