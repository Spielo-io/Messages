package io.spielo.messages;

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
        return (short)this.code.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
        builder.addString(this.code);
    }

    public static Message parse(BufferIterator iterator) {
        MessageHeader header = MessageHeader.parse(iterator);
        String code = iterator.getString();

        return new CreateLobbyResponseMessage(header, code);
    }
}
