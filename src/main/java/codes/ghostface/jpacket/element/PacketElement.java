package codes.ghostface.jpacket.element;

import codes.ghostface.jpacket.builder.PacketBuilder;
import codes.ghostface.jpacket.message.Messages;
import codes.ghostface.jpacket.module.Side;
import codes.ghostface.jpacket.module.WriteMode;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public interface PacketElement<T extends PacketBuilder<?>> {

    @NotNull WriteMode getMode();

    @NotNull Side getSide();

    @NotNull T getBuilder();

    @NotNull Messages getMessages();

    @NotNull InputStream getInputStream();

}
