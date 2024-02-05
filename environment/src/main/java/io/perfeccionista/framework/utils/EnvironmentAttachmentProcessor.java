package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import org.jetbrains.annotations.NotNull;

public interface EnvironmentAttachmentProcessor {

    void process(@NotNull TextAttachmentEntry environmentTextAttachment);

}
