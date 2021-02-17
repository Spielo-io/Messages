package io.spielo;

import io.spielo.util.BufferBuilder;
import io.spielo.util.BufferIterator;

import java.nio.charset.StandardCharsets;

public class JoinLobbyMessage extends Message{
    private final String code;
    private final String displayName;

    public JoinLobbyMessage(final MessageHeader header, final String code, final String displayName) {
        super(header);
        this.code = code;
        this.displayName = displayName;
    }

    @Override
    protected short getBodyLength() {
        return (short)(code.getBytes(StandardCharsets.UTF_8).length + displayName.getBytes(StandardCharsets.UTF_8).length);
    }

    @Override
    protected void bodyIntoBuffer(BufferBuilder builder) {
        builder.addString(this.code);
        builder.addString(this.displayName);
    }

    public static Message parse(BufferIterator iterator) {
        MessageHeader header = MessageHeader.parse(iterator);
        String j = iterator.exhaustString();
        String code = j.substring(0, 6);
        String display_name = j.substring(6);
        return new JoinLobbyMessage(header, code, display_name);
    }
}
