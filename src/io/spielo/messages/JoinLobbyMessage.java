package io.spielo.messages;

import java.nio.charset.StandardCharsets;

import io.spielo.messages.util.BufferBuilder;
import io.spielo.messages.util.BufferIterator;

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
        String code = iterator.getString();
        String display_name = iterator.getString();
        return new JoinLobbyMessage(header, code, display_name);
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
