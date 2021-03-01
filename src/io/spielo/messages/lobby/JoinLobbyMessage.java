package io.spielo.messages.lobby;

import java.nio.charset.StandardCharsets;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

public class JoinLobbyMessage extends Message{
    private final String code;
    private final String username;

    public JoinLobbyMessage(final MessageHeader header, final String code, final String username) {
        super(header);
        this.code = code;
        this.username = username;
    }

    @Override
	public short getBodyLength() {
        return (short)(code.getBytes(StandardCharsets.UTF_8).length + username.getBytes(StandardCharsets.UTF_8).length + 2);
    }

    @Override
	public void bodyIntoBuffer(BufferBuilder builder) {
        builder.addString(this.code);
        builder.addString(this.username);
    }

    public static Message parse(BufferIterator iterator, MessageHeader header) {
        String code = iterator.getString();
        String username = iterator.getString();
        return new JoinLobbyMessage(header, code, username);
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return username;
    }
}
