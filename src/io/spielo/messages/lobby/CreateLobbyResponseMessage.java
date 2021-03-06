package io.spielo.messages.lobby;

import io.spielo.messages.Message;
import io.spielo.messages.MessageHeader;
import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

import java.nio.charset.StandardCharsets;

public class CreateLobbyResponseMessage extends Message {
    private final String code;

    public CreateLobbyResponseMessage(final MessageHeader header, final String code) {
        super(header);
        this.code = code;
    }

    @Override
    protected short getBodyLength() {
        return (short)(this.code.getBytes(StandardCharsets.UTF_8).length + 1);
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
        builder.addString(this.code);
    }

    public static Message parse(BufferIterator iterator, MessageHeader header) {
        String code = iterator.getString();

        return new CreateLobbyResponseMessage(header, code);
    }

    public String getCode() {
        return code;
    }
}
