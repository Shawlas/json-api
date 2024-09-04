package codes.ghostface.factory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public interface JsonPacketModel {

    @NotNull String get(@NotNull String key);

    void add(@NotNull String key, @NotNull String value);

    @Nullable Comparator<@NotNull String> getComparator();

    @NotNull JsonPacketModel serialize(@NotNull Packet packet);

    @NotNull Packet deserialize(@NotNull JsonPacketModel packet);

}
