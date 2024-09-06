package codes.ghostface.models;

import codes.laivy.address.domain.SLD;
import codes.laivy.address.domain.TLD;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

public final class Email implements CharSequence, Serializable {

    // Static initializers

    public static boolean validate(@NotNull String username) {
        return username.matches("[a-zA-Z0-9._%+-]{1,64}");
    }

    // Objects

    private final @NotNull String username;
    private final @NotNull SLD sld;
    private final @NotNull TLD tld;

    public Email(@NotNull String username, @NotNull SLD sld, @NotNull TLD tld) {
        if (!validate(username)) {
            throw new IllegalArgumentException("email username is not valid");
        }
        this.username = username;
        this.sld = sld;
        this.tld = tld;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @NotNull SLD getSld() {
        return sld;
    }

    public @NotNull TLD getTld() {
        return tld;
    }

    @Override
    public @NotNull String toString() {
        return getUsername() + "@" + getSld() + "." + getTld();
    }

    // Natives

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Email email = (Email) object;
        return Objects.equals(username, email.username) && Objects.equals(sld, email.sld) && Objects.equals(tld, email.tld);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, sld, tld);
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
