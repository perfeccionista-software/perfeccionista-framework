package io.perfeccionista.framework.exceptions.attachments;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BigTextAttachmentEntry extends FileAttachmentEntry<String> {

    protected BigTextAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static BigTextAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new BigTextAttachmentEntry(name, content);
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("");
    }

    @Override
    public @NotNull String getFileExtension() {
        return "txt";
    }

}
