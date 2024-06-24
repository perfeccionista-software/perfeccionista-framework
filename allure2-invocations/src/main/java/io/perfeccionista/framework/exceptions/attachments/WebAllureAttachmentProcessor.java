package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.EmptyAttachment;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_ATTACHMENT_ENTRY;

public class WebAllureAttachmentProcessor extends DefaultAttachmentProcessor {
    private static final Logger logger = LoggerFactory.getLogger(WebAllureAttachmentProcessor.class);

    protected Environment environment;

    public WebAllureAttachmentProcessor(@NotNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public String processAttachment(@NotNull Attachment attachment) {
        attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof FileAttachmentEntry<?>)
                .forEach(entry -> {
                    FileAttachmentEntry<?> fileAttachmentEntry = (FileAttachmentEntry<?>) entry;
                    if (fileAttachmentEntry instanceof HtmlAttachmentEntry) {
                        Allure.addAttachment(fileAttachmentEntry.getName(), "text/html", fileAttachmentEntry.getDescription());
                    } else if (fileAttachmentEntry instanceof JsonAttachmentEntry) {
                        Allure.addAttachment(fileAttachmentEntry.getName(), "application/json", fileAttachmentEntry.getDescription());
                    } else if (fileAttachmentEntry instanceof ScreenshotAttachmentEntry) {
                        Screenshot screenshot = ((ScreenshotAttachmentEntry) entry).getContent()
                                .orElseThrow(() -> EmptyAttachment.exception(EMPTY_ATTACHMENT_ENTRY.getMessage()));
                        Allure.addAttachment("Web Browser Screenshot",
                                "image/png",
                                new ByteArrayInputStream(screenshot.getRaw()),
                                "png");
                    } else if (fileAttachmentEntry instanceof BigTextAttachmentEntry) {
                        Allure.addAttachment(fileAttachmentEntry.getName(), "text/plain", fileAttachmentEntry.getDescription());
                    }
                });

        attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof TextAttachmentEntry)
                .forEach(entry -> Allure.addAttachment(entry.getName(), "text/plain", entry.getDescription()));

        attachment.getMainAttachmentEntry()
                .ifPresent(entry -> Allure.addAttachment(entry.getName(), "text/plain", entry.getDescription()));

        return "";
    }

}
