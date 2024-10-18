package codes.ghostface.jpacket.data;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarInputStream;

public interface DataPacket extends Closeable {

    static @NotNull DataPacket create(@NotNull InputStream input) throws IOException {
        if (input.available() > 8192) {
            return new DataCachePacket(input);
        } else {
            return new DataBufferedPacket(input);
        }
    }

    static @NotNull DataPacket create(byte @NotNull [] bytes) throws IOException {
        if (bytes.length == 0) {
            throw new IOException("Bytes cannot be null");
        } else if (bytes.length > 8192) {
            return new DataCachePacket(bytes);
        } else {
            return new DataBufferedPacket(bytes);
        }
    }

    @NotNull InputStream getInputStream() throws IOException;

    void write(@NotNull OutputStream outputStream) throws IOException;

    @Override
    void close() throws IOException;

    int size();
}
