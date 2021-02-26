package io.spielo.messages.lobby;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Lobby;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

import java.nio.charset.StandardCharsets;


public class JoinLobbyResponseMessage extends Message{

    private final JoinLobbyResponseCode responseCode;
    private final String username;

    public JoinLobbyResponseMessage(final short receiverID, final JoinLobbyResponseCode responseCode, final String playerName) {
        super(new MessageHeader(0, receiverID, MessageType1.LOBBY, MessageType2Lobby.JOINRESPONSE, System.currentTimeMillis()));
        
        this.responseCode = responseCode;
        this.username = playerName;
    }

    public JoinLobbyResponseMessage(final MessageHeader header, final JoinLobbyResponseCode responseCode, final String playerName) {
        super(header);
        
        this.responseCode = responseCode;
        this.username = playerName;
    }

    @Override
    protected short getBodyLength() {
        return (short) (2 + username.getBytes(StandardCharsets.UTF_8).length);
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
        builder.addByte(responseCode.getByte()).addString(username);
    }

    public static Message parse(BufferIterator iterator) {
        MessageHeader header = MessageHeader.parse(iterator);
        JoinLobbyResponseCode responseCode = iterator.getNextByteEnum(JoinLobbyResponseCode.class);
        String username = iterator.getString();

        return new JoinLobbyResponseMessage(header, responseCode, username);
    }

    public JoinLobbyResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String getPlayerName(){
        return username;
    }
}
