package io.spielo.messages;

import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Lobby;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

import java.nio.charset.StandardCharsets;

public class JoinLobbyResponseMessage extends Message{

    private final short responseCode;
    //0 = Failed, 1 = Success, 2 = Other Player Joined
    private final String playerName;

    public JoinLobbyResponseMessage(short receiverID, short responseCode, final String playerName) {
        super(new MessageHeader(0, receiverID, MessageType1.LOBBY, MessageType2Lobby.JOINRESPONSE, System.currentTimeMillis()));
        this.responseCode = responseCode;
        this.playerName = playerName;
    }

    public JoinLobbyResponseMessage(MessageHeader header, short responseCode, final String playerName) {
        super(header);
        this.responseCode = responseCode;
        this.playerName = playerName;
    }

    @Override
    protected short getBodyLength() {
        return (short) (2 + playerName.getBytes(StandardCharsets.UTF_8).length);
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
        builder.addShort(responseCode);
        builder.addString(playerName);
    }

    public static Message parse(BufferIterator iterator) {
        MessageHeader header = MessageHeader.parse(iterator);
        short a = iterator.getNextShort();
        String b = iterator.getString();

        return new JoinLobbyResponseMessage(header, a, b);
    }

    public short getResponseCode() {
        return this.responseCode;
    }

    public String getPlayerName(){
        return playerName;
    }
}
