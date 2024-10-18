package codes.ghostface.jpacket.data;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

public class DataCachePacket implements DataPacket {

    protected final @NotNull Object lock = new Object();
    private final @NotNull File file;
    private final int size;

    private volatile boolean closed = false;

    public DataCachePacket(@NotNull InputStream stream) throws IOException {
        this.file = File.createTempFile("data", "-cache_packet");
        file.deleteOnExit();

        this.size = uptdate(stream);
    }

    public DataCachePacket(byte @NotNull [] bytes) throws IOException {
        this.file = File.createTempFile("data", "-cache_packet");
        file.deleteOnExit();

        this.size = bytes.length;
        uptdate(new ByteArrayInputStream(bytes));
    }

    private int uptdate(@NotNull InputStream stream) throws IOException {
        try (@NotNull FileOutputStream output = new FileOutputStream(file)) {
            byte @NotNull [] bytes = new byte[8192];

            int read;
            int size = 0;
            while ((read = stream.read(bytes)) != -1) {
                size += read;
                output.write(bytes, 0, read);
                output.flush();
            }

            return size;
        }
    }

    public @NotNull File getFile() {
        return file;
    }

    @Override
    public @NotNull InputStream getInputStream() throws IOException {
        if (closed) {
            throw new IOException("Data is close");
        } else synchronized (lock) {
            return Files.newInputStream(file.toPath());
        }
    }

    @Override
    public void write(@NotNull OutputStream output) throws IOException {
        if (closed) {
            throw new IOException("Data is close");
        } else synchronized (lock) {
            try (@NotNull InputStream input = getInputStream()) {
                byte @NotNull [] bytes = new byte[8192];

                int read;
                while ((read = input.read(bytes)) != -1) {
                    output.write(bytes, 0, read);
                    output.flush();
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("Data already is close");
        } else try {
            synchronized (lock) {
                Files.delete(file.toPath());
            }
        } finally {
            closed = true;
        }
    }

    @Override
    public int size() {
        return size;
    }
}
