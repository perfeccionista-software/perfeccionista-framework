package io.perfeccionista.framework.screenshots;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.time.LocalDateTime;

import static io.perfeccionista.framework.utils.FileUtils.readBinaryFile;
import static io.perfeccionista.framework.utils.FileUtils.writeBinaryFile;

public class JpegScreenshot implements Screenshot {

    private final LocalDateTime created;
    private final byte[] raw;
    private final String mimeType;
    private String name = null;
    private String description = null;

    public JpegScreenshot(byte[] raw) {
        this.created = LocalDateTime.now();
        this.raw = raw;
        this.mimeType = "image/jpeg";
    }

    public static JpegScreenshot from(byte[] raw) {
        return new JpegScreenshot(raw);
    }

    public static JpegScreenshot from(Path path) {
        return new JpegScreenshot(readBinaryFile(path));
    }

    public JpegScreenshot withName(String name) {
        this.name = name;
        return this;
    }

    public JpegScreenshot withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public byte[] getRaw() {
        return raw;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public JpegScreenshot writeToFile(@NotNull Path path) {
        writeBinaryFile(path, this.raw);
        return this;
    }

}
