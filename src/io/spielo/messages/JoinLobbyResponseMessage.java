package io.spielo.messages;

import io.spielo.messages.types.MessageType1;
import io.spielo.messages.types.MessageType2Lobby;
import io.spielo.messages.util.BufferBuilder;

public class JoinLobbyResponseMessage extends Message{

    private final byte responseCode;

    public JoinLobbyResponseMessage(short receiverID, int responseCode) {
        super(new MessageHeader(0, receiverID, MessageType1.LOBBY, MessageType2Lobby.JOINRESPONSE, System.currentTimeMillis()));
        this.responseCode = (byte) responseCode;
    }

    @Override
    protected short getBodyLength() {
        return 1;
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
        builder.addByte(responseCode);
    }

    public byte getResponseCode() {
        return this.responseCode;
    }
}
