package codes.ghostface.jpacket.message;

import codes.ghostface.jpacket.module.Target;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public interface Messages extends Collection<@NotNull Message<?>> {

    @NotNull Target target();

    boolean put(@NotNull Message<?> message);

    boolean add(@NotNull Message<?> message);

    boolean remove(@NotNull String name);

    @NotNull Stream<@NotNull Message<?>> stream();

    @NotNull Iterator<Message<?>> iterator();

    void clear();

    @NotNull Collection<Message<?>> get(@NotNull Target target);

    @Override
    default int size() {
        return (int) stream().count();
    }

    default boolean contains(@NotNull String name) {
        return stream().anyMatch(message -> message.getName().equalsIgnoreCase(name));
    }

    default boolean remove(@NotNull Message<?> message) {
        return removeIf(msg -> contains(message));
    }

    default boolean contains(@NotNull Message<?> message) {
        return contains(message.getName());
    }

    default @NotNull Optional<Message<?>> get(@NotNull String name) {
        return stream().filter(message -> message.getName().equalsIgnoreCase(name)).findFirst();
    }
}
