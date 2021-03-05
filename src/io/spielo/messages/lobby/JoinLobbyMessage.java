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
    protected short getBodyLength() {
    	short length = (short) (username.getBytes(StandardCharsets.UTF_8).length + 1);
    	if (code != null)
    		length += code.getBytes(StandardCharsets.UTF_8).length + 1;
    	
    	return length;
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
    	if (code != null)
    		builder.addString(this.code);
        builder.addString(this.username);
    }

    public static Message parse(BufferIterator iterator, MessageHeader header) {
        String code = iterator.getString();
        String username;
        if (iterator.hasNextString()) {
        	username = iterator.getString();
        }
        else {
        	username = code;
        	code = null;
        }
        return new JoinLobbyMessage(header, code, username);
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return username;
    }
}
