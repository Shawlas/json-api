package com.ghostface.dev.application.util;

import org.jetbrains.annotations.NotNull;

public class Username implements CharSequence {

    public static boolean validate(@NotNull String username) {
        if (username.isEmpty()) {
            System.err.println("Username unavailable");
            return false;
        } else if (!Character.isLetter(username.charAt(0))) {
            System.err.println("The beginning must be a Letter");
            return false;
        } else if (!(username.length() > 4 && username.length() < 14)) {
            System.err.println("Username must have between 4 and 14 characters");
            return false;
        } else if (!username.matches("^[a-zA-Z0-9._-]+$")) {
            System.err.println("allowed Characters: ._- ");
            return false;
        }
        return true;
    }

    private Username() {
        throw new UnsupportedOperationException();
    }

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
