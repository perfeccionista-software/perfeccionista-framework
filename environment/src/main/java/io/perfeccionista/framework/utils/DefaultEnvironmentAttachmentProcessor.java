package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEnvironmentAttachmentProcessor implements EnvironmentAttachmentProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DefaultEnvironmentAttachmentProcessor.class);

    @Override
    public void process(@NotNull TextAttachmentEntry environmentTextAttachment) {
        environmentTextAttachment.getContent()
                        .ifPresent(logger::info);
    }

}
