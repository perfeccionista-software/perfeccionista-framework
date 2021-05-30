package io.perfeccionista.framework.exceptions.attachments;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FileAttachmentEntry<T> extends AttachmentEntry<T> {

    protected FileAttachmentEntry(@NotNull String name, @Nullable T content) {
        super(name, content);
    }

    public abstract @NotNull String getFileExtension();

}
