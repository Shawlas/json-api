package codes.ghostface.jpacket.module;

public enum Target {

    SERVER(true, false),
    CLIENT(false, true),
    BOTH(true, true)
    ;

    private final boolean server;
    private final boolean client;

    Target(boolean server, boolean client) {
        this.server = server;
        this.client = client;
    }

    public boolean isServer() {
        return server;
    }

    public boolean isClient() {
        return client;
    }

    public boolean isBoth() {
        return client && server;
    }
}
