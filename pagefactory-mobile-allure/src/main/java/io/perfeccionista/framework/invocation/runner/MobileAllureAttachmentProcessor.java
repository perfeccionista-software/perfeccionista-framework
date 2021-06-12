package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.EmptyAttachment;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.BigTextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.DefaultAttachmentProcessor;
import io.perfeccionista.framework.exceptions.attachments.FileAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ScreenshotAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceService;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_ATTACHMENT_ENTRY;
import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;
import static io.perfeccionista.framework.utils.FileUtils.writeBinaryFile;
import static io.perfeccionista.framework.utils.FileUtils.writeTextFile;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.StringUtils.isBlank;

public class MobileAllureAttachmentProcessor extends DefaultAttachmentProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MobileAllureAttachmentProcessor.class);

    protected Environment environment;
    protected InvocationName invocationName;

    public MobileAllureAttachmentProcessor(@NotNull Environment environment, @NotNull InvocationName invocationName) {
        this.environment = environment;
        this.invocationName = invocationName;
    }

    @Override
    public String processAttachment(@NotNull Attachment attachment) {

        // TODO: Из name вытаскиваем операцию и оперейшн резалт

        String fileAttachmentsDescription = attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof FileAttachmentEntry<?>)
                .map(entry -> {
                    FileAttachmentEntry<?> fileAttachmentEntry = (FileAttachmentEntry<?>) entry;
                    String fileName = entry.getName() + "_" + createId() + "." + fileAttachmentEntry.getFileExtension();
                    Path filePath = Path.of(ATTACHMENT_DIR + File.separator + fileName);
                    if (fileAttachmentEntry instanceof HtmlAttachmentEntry) {
                        String content = ((HtmlAttachmentEntry) fileAttachmentEntry).getContent().orElse("");
                        deleteFileIgnoreExceptions(filePath);
                        writeTextFile(filePath, content);
                        Allure.addAttachment(fileAttachmentEntry.getName(), "text/html", fileAttachmentEntry.getDescription());
                    } else if (fileAttachmentEntry instanceof JsonAttachmentEntry) {
                        String content = ((JsonAttachmentEntry) fileAttachmentEntry).getContent().orElse(createObjectNode())
                                .toPrettyString();
                        deleteFileIgnoreExceptions(filePath);
                        writeTextFile(filePath, content);
                        Allure.addAttachment(fileAttachmentEntry.getName(), "application/json", fileAttachmentEntry.getDescription());
                    } else if (fileAttachmentEntry instanceof ScreenshotAttachmentEntry) {
                        Screenshot screenshot = ((ScreenshotAttachmentEntry) entry).getContent()
                                .orElseThrow(() -> EmptyAttachment.exception(EMPTY_ATTACHMENT_ENTRY.getMessage()));
                        deleteFileIgnoreExceptions(filePath);
                        writeBinaryFile(Path.of(fileName), screenshot.getRaw());
                    } else if (fileAttachmentEntry instanceof BigTextAttachmentEntry) {
                        String content = ((BigTextAttachmentEntry) fileAttachmentEntry).getContent().orElse("");
                        deleteFileIgnoreExceptions(filePath);
                        writeTextFile(filePath, content);
                        Allure.addAttachment(fileAttachmentEntry.getName(), "text/plain", fileAttachmentEntry.getDescription());
                    }
                    return entry.getName() + " " + filePath.toAbsolutePath().toString();
                }).collect(Collectors.joining("\n"));

        String stringAttachmentsDescription = attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof TextAttachmentEntry)
                .map(entry -> {
                    String fileName = entry.getName() + "_" + createId() + ".txt";
                    Path filePath = Path.of(ATTACHMENT_DIR + File.separator + fileName);
                    String content = ((TextAttachmentEntry) entry).getContent().orElse("");
                    deleteFileIgnoreExceptions(filePath);
                    writeTextFile(filePath, content);
                    Allure.addAttachment(entry.getName(), "text/plain", entry.getDescription());
                    return entry.getName() + " " + filePath.toAbsolutePath().toString();
                })
                .collect(Collectors.joining("\n"));

        String mainAttachmentDescription = attachment.getMainAttachmentEntry()
                .map(entry -> getDelimiter() + entry.getName() + "\n" + getDelimiter() + entry.getDescription())
                .orElse("");

        String screenshotAttachmentDescription = takeScreenshot(environment)
                .map(screenshot -> {
                    String fileName = "Screenshot_" + createId() + ".png";
                    Path filePath = Path.of(ATTACHMENT_DIR + File.separator + fileName);
                    deleteFileIgnoreExceptions(filePath);
                    writeBinaryFile(filePath, screenshot.getRaw());
                    Allure.addAttachment("Mobile Device Screenshot",
                            "image/png",
                            new ByteArrayInputStream(screenshot.getRaw()),
                            "png");
                    return "Screenshot " + filePath.toAbsolutePath().toString();
                }).orElse("");

        if (isBlank(fileAttachmentsDescription)
                && isBlank(stringAttachmentsDescription)
                && isBlank(mainAttachmentDescription)
                && isBlank(screenshotAttachmentDescription)) {
            return "";
        }

        logger.info(() -> "Attachments:\n"
                + getDelimiter()
                + (isBlank(fileAttachmentsDescription) ? "" : fileAttachmentsDescription + "\n")
                + (isBlank(stringAttachmentsDescription) ? "" : stringAttachmentsDescription + "\n")
                + (isBlank(mainAttachmentDescription) ? "" : mainAttachmentDescription + "\n")
                + (isBlank(screenshotAttachmentDescription) ? "" : screenshotAttachmentDescription + "\n")
                + getDelimiter());

        return "";
    }

    protected Optional<Screenshot> takeScreenshot(@NotNull Environment environment) {
        MobileDeviceService mobileDeviceService = environment.getService(MobileDeviceService.class);
        if (mobileDeviceService.isActiveDispatcherRunning()) {
            return Optional.of(mobileDeviceService.getActiveDispatcher()
                    .screen()
                    .getPageScreenshot());
        }
        return Optional.empty();
    }

}
