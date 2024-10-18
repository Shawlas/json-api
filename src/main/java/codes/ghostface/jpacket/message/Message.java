package codes.ghostface.jpacket.message;

import codes.ghostface.jpacket.module.Target;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Message<T> {

    @NotNull Target target();

    @NotNull String getName();

    @NotNull T getValue();

    @Override
    boolean equals(@Nullable Object object);
    @Override
    int hashCode();
}
