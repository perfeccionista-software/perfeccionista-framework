package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.exceptions.EmptyAttachment;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_ATTACHMENT_ENTRY;

public class ScreenshotAttachmentEntry extends FileAttachmentEntry<Screenshot> {

    protected ScreenshotAttachmentEntry(String name, Screenshot content) {
        super(name, content);
    }

    public static ScreenshotAttachmentEntry of(@NotNull String name, @NotNull Screenshot content) {
        return new ScreenshotAttachmentEntry(name, content);
    }

    // TODO: Можно здесь выводить параметры скриншота: тип, имя, размер
    @Override
    public String getDescription() {
        return "No text description for screenshot";
    }

    @Override
    public @NotNull String getFileExtension() {
        return getContent().orElseThrow(() -> EmptyAttachment.exception(EMPTY_ATTACHMENT_ENTRY.getMessage()))
                .getFileExtension();
    }

}
