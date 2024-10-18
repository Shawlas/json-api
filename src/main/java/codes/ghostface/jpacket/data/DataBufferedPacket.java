package codes.ghostface.jpacket.data;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class DataBufferedPacket implements DataPacket {

    private byte[] bytes;
    private volatile boolean closed = false;
    private final int size;

    public DataBufferedPacket(@NotNull InputStream inputStream) throws IOException {
        try (@NotNull ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte @NotNull [] bytes = new byte[8192];

            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                output.write(bytes, 0, read);
                output.flush();
            }

            this.bytes = output.toByteArray();
            this.size = bytes.length;
        }
    }

    public DataBufferedPacket(byte @NotNull [] bytes) {
        this.bytes = bytes;
        this.size = bytes.length;
    }

    @Override
    public @NotNull InputStream getInputStream() throws IOException {
        if (closed) {
            throw new IOException("Data is close");
        }
        return new ByteArrayInputStream(bytes);
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public void write(@NotNull OutputStream outputStream) throws IOException {
        if (closed) {
            throw new IOException("Data is close");
        } else {
            outputStream.write(bytes);
            outputStream.flush();
        }
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("Data already is close");
        } else {
            bytes = new byte[0];
            closed = true;
        }
    }

    @Override
    public int size() {
        return size;
    }
}
