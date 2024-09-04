package codes.ghostface.factory.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public interface JsonPacketModel {

    @NotNull String get(@NotNull String key);

    void add(@NotNull String key, @NotNull String value);

    @Nullable Comparator<@NotNull String> getComparator();

}
