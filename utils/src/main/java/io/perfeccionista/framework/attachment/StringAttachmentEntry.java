package io.perfeccionista.framework.attachment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringAttachmentEntry extends AttachmentEntry<String> {

    private StringAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static StringAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new StringAttachmentEntry(name, content);
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
