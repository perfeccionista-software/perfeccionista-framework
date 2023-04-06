package io.perfeccionista.framework.screenshots;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

import static io.perfeccionista.framework.utils.FileUtils.readBinaryFile;
import static io.perfeccionista.framework.utils.FileUtils.writeTextFile;

public class Base64Screenshot implements Screenshot {

    private final LocalDateTime created;
    private final byte[] raw;
    private final String mimeType;
    private String name = null;
    private String description = null;
    private String fileExtension = null;

    public Base64Screenshot(byte[] raw) {
        this.created = LocalDateTime.now();
        this.raw = raw;
        this.mimeType = "text/plain";
        this.fileExtension = "txt";
    }

    public static Base64Screenshot from(byte[] raw) {
        return new Base64Screenshot(raw);
    }

    public static Base64Screenshot from(Path path) {
        return new Base64Screenshot(readBinaryFile(path));
    }

    public Base64Screenshot withName(String name) {
        this.name = name;
        return this;
    }

    public Base64Screenshot withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
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
    public String getFileExtension() {
        return fileExtension;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public byte[] getRaw() {
        return raw;
    }

    @Override
    public int getSize() {
        return Objects.isNull(raw) ? 0 : raw.length;
    }

    @Override
    public Base64Screenshot writeToFile(@NotNull Path path) {
        String screenshotToBase64String = new String(Base64.getEncoder().encode(this.raw));
        writeTextFile(path, screenshotToBase64String);
        return this;
    }

}
