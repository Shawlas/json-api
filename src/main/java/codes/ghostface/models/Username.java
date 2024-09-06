package codes.ghostface.models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

public final class Username implements CharSequence, Serializable {

    public static boolean validate(@NotNull String s) {
        if (!Character.isLetter(s.charAt(0))) {
            return false;
        }
        return s.matches("^[a-zA-Z0-9-_]{4,16}+$");
    }

    // Objects

    private final @NotNull String string;

    public Username(@NotNull String string) {
        if (!validate(string)) {
            throw new IllegalArgumentException("String '" + string + "' is not valid");
        }
        this.string = string;
    }

    @Override
    public @NotNull String toString() {
        return string;
    }

    // Natives

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Username username = (Username) object;
        return Objects.equals(string, username.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }

    // Charsequence

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start,end);
    }
}
