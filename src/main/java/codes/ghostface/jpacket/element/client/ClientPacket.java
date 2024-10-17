package codes.ghostface.jpacket.element.client;

import codes.ghostface.jpacket.builder.PacketBuilder;
import codes.ghostface.jpacket.element.PacketElement;
import codes.ghostface.jpacket.exception.IllegalTargetException;
import codes.ghostface.jpacket.message.Messages;
import codes.ghostface.jpacket.module.Side;
import codes.ghostface.jpacket.module.WriteMode;
import org.jetbrains.annotations.NotNull;


public abstract class ClientPacket<T extends PacketBuilder<?>> implements PacketElement<T> {

    private final @NotNull Messages messages;
    private final @NotNull WriteMode mode;

    protected ClientPacket(@NotNull WriteMode mode, @NotNull Messages messages) {
        if (!messages.target().isClient())
            throw new IllegalTargetException();
        this.messages = messages;
        this.mode = mode;
    }

    @Override
    public final @NotNull Side getSide() {
        return Side.CLIENT;
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
