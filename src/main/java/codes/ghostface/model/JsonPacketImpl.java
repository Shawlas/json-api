package codes.ghostface.model;

import codes.ghostface.factory.model.JsonPacketModel;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public final class JsonPacketImpl implements JsonPacketModel {

    // Static initializers

    private static final @NotNull Comparator<@NotNull String> comparator;

    static {
        comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals("type")) {
                    return -1;
                } else if (o2.equals("type")) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        };
    }

    // Objects

    private final @NotNull Map<@NotNull String, @NotNull String> map = new TreeMap<>(comparator);

    public JsonPacketImpl() {
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        this.map.put(key, value);
    }

    @Override
    public @NotNull String get(@NotNull String key) {
        return this.map.get(key);
    }

    @Override
    public @NotNull Comparator<@NotNull String> getComparator() {
        return comparator;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
