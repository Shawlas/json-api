package codes.ghostface.jpacket.element.server;

import codes.ghostface.jpacket.builder.PacketBuilder;
import codes.ghostface.jpacket.element.PacketElement;
import codes.ghostface.jpacket.exception.IllegalTargetException;
import codes.ghostface.jpacket.message.Messages;
import codes.ghostface.jpacket.module.Side;
import codes.ghostface.jpacket.module.WriteMode;
import org.jetbrains.annotations.NotNull;


public abstract class ServerPacket<T extends PacketBuilder<?>> implements PacketElement<T> {

    private final @NotNull Messages messages;
    private final @NotNull WriteMode mode;

    protected ServerPacket(@NotNull WriteMode mode, @NotNull Messages messages) {
        if (!messages.target().isServer())
            throw new IllegalTargetException();

        this.messages = messages;
        this.mode = mode;
    }

    @Override
    public final @NotNull Side getSide() {
        return Side.SERVER;
    }

    @Override
    public @NotNull WriteMode getMode() {
        return mode;
    }

    @Override
    public @NotNull Messages getMessages() {
        return messages;
    }
}
