package codes.shawlas.json.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.Pattern;

public class MemberName implements CharSequence, Comparable<@NotNull MemberName> {

    // Static initializers

    public static @NotNull Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]+(?:[ _-][a-zA-Z0-9]+)*$");

    public static boolean isValid(@NotNull String s) {
        return s.matches(PATTERN.pattern());
    }

    public static @NotNull MemberName of(@NotNull String name) {
        if (!isValid(name)) throw new IllegalArgumentException("Cannot create the string '" + name + "' as a valid name");
        return new MemberName(name);
    }

    // Objects

    private final @NotNull String name;

    private MemberName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public @NotNull String toString() {
        return name;
    }

    @Override
    public int compareTo(@NotNull MemberName that) {
        if (this.name.equals("type")) {
            return that.name.equals("type") ? 0 : -1;
        } else if (this.name.equals("id")) {
            return that.name.equals("type") ? 1 : (that.name.equals("id") ? 0 : -1);
        }

        return 1;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object == null) return false;
        if (object.getClass() != String.class && getClass() != object.getClass()) return false;
        return Objects.equals(name.toLowerCase(), object instanceof String ? ((String) object).toLowerCase() : ((MemberName) object).name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name.toLowerCase());
    }
}