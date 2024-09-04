package codes.ghostface;

import codes.ghostface.impl.request.ClientAuthPacket;
import codes.ghostface.entity.Email;
import codes.ghostface.entity.Password;
import org.jetbrains.annotations.NotNull;

public final class Main {
    public static void main(String[] args) {
        @NotNull ClientAuthPacket authPacket = new ClientAuthPacket(new Email("seycy", "@test", ".com"), new Password("seuPai123"));

        System.out.println();
    }
}
