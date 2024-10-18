package codes.ghostface.jpacket.element;

import codes.ghostface.jpacket.builder.PacketBuilder;
import codes.ghostface.jpacket.data.DataPacket;
import codes.ghostface.jpacket.message.Messages;
import codes.ghostface.jpacket.module.Side;
import codes.ghostface.jpacket.module.WriteMode;
import org.jetbrains.annotations.NotNull;

public interface PacketElement<T> {

    @NotNull Side getSide();

    @NotNull WriteMode getMode();

    @NotNull PacketBuilder<T> getBuilder();

    @NotNull Messages getMessages();

    @NotNull DataPacket getData();

}
