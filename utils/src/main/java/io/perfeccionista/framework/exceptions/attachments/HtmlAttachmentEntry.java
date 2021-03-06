package io.perfeccionista.framework.exceptions.attachments;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HtmlAttachmentEntry extends FileAttachmentEntry<String> {

    protected HtmlAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static HtmlAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new HtmlAttachmentEntry(name, content);
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("");
    }

    @Override
    public @NotNull String getFileExtension() {
        return "html";
    }

}
