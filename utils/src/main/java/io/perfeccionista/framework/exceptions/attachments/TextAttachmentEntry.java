package io.perfeccionista.framework.exceptions.attachments;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class TextAttachmentEntry extends AttachmentEntry<String> {

    protected TextAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static TextAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new TextAttachmentEntry(name, content);
    }

    public static TextAttachmentEntry of(@NotNull String name, @NotNull Set<String> content) {
        return new TextAttachmentEntry(name, String.join("\n", content));
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
