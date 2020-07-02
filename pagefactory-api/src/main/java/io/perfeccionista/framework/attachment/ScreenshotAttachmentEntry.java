package io.perfeccionista.framework.attachment;

import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScreenshotAttachmentEntry extends AttachmentEntry<Screenshot> {

    protected ScreenshotAttachmentEntry(String name, Screenshot content) {
        super(name, content);
    }

    public static ScreenshotAttachmentEntry of(@NotNull String name, @Nullable Screenshot content) {
        return new ScreenshotAttachmentEntry(name, content);
    }

    @Override
    public String getDescription() {
        return "No text description for screenshot";
    }

}
