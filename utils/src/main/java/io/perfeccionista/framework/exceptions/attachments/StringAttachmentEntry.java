package io.perfeccionista.framework.exceptions.attachments;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class StringAttachmentEntry extends AttachmentEntry<String> {

    protected StringAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static StringAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new StringAttachmentEntry(name, content);
    }

    public static StringAttachmentEntry of(@NotNull String name, @NotNull Set<String> content) {
        return new StringAttachmentEntry(name, String.join("\n", content));
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
