package codes.ghostface.main;

import codes.ghostface.ClientPacket;
import codes.ghostface.ServerPacket;
import codes.ghostface.impl.client.MessagePacket;
import codes.ghostface.impl.server.ServerResponsePacket;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;


public final class Main {
    public static void main(String[] args) {
        @NotNull ClientPacket packet = new MessagePacket("hello world");
        System.out.println(packet.getUtils().deserialize(packet));

        @NotNull ServerPacket server = new ServerResponsePacket("success", OffsetDateTime.now());
        System.out.println(server.getUtils().deserialize(server));
    }
}
