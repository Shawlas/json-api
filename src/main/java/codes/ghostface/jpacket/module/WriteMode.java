package codes.ghostface.jpacket.module;

public enum WriteMode {

    DOUBLE_WRITE(true, false),
    SINGLE_WRITE(false, true);

    private final boolean doubleWrite;
    private final boolean singleWrite;

    WriteMode(boolean doubleWrite, boolean singleWrite) {
        this.doubleWrite = doubleWrite;
        this.singleWrite = singleWrite;
    }

    public boolean isDoubleWrite() {
        return doubleWrite;
    }

    public boolean isSingleWrite() {
        return singleWrite;
    }
}
