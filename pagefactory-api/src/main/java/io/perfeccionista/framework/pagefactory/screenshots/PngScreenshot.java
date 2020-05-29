package io.perfeccionista.framework.pagefactory.screenshots;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.time.LocalDateTime;

import static io.perfeccionista.framework.utils.FileUtils.readBinaryFile;
import static io.perfeccionista.framework.utils.FileUtils.writeBinaryFile;

public class PngScreenshot implements Screenshot {

    private final LocalDateTime created;
    private final byte[] raw;
    private final String mimeType;
    private String name = null;
    private String description = null;

    public PngScreenshot(byte[] raw) {
        this.created = LocalDateTime.now();
        this.raw = raw;
        this.mimeType = "image/png";
    }

    public static PngScreenshot from(byte[] raw) {
        return new PngScreenshot(raw);
    }

    public static PngScreenshot from(Path path) {
        return new PngScreenshot(readBinaryFile(path));
    }

    public PngScreenshot withName(String name) {
        this.name = name;
        return this;
    }

    public PngScreenshot withDescription(String description) {
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
    public PngScreenshot writeToFile(@NotNull Path path) {
        writeBinaryFile(path, this.raw);
        return this;
    }

}
