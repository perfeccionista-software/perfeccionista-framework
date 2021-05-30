package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.exceptions.EmptyAttachment;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_ATTACHMENT_ENTRY;
import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;
import static io.perfeccionista.framework.utils.FileUtils.writeBinaryFile;
import static io.perfeccionista.framework.utils.FileUtils.writeTextFile;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.StringUtils.isBlank;

public class DefaultAttachmentProcessor implements AttachmentProcessor {
    protected static final String DELIMITER = "------------------------------------------------------------------------------------------------------------------------\n";
    protected static final String ATTACHMENT_DIR = "build" + File.separator + "attachments";

    @Override
    public String processAttachment(@NotNull Attachment attachment) {

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
                    } else if (fileAttachmentEntry instanceof JsonAttachmentEntry) {
                        String content = ((JsonAttachmentEntry) fileAttachmentEntry).getContent().orElse(createObjectNode())
                                .toPrettyString();
                        deleteFileIgnoreExceptions(filePath);
                        writeTextFile(filePath, content);
                    } else if (fileAttachmentEntry instanceof ScreenshotAttachmentEntry) {
                        Screenshot screenshot = ((ScreenshotAttachmentEntry) entry).getContent()
                                .orElseThrow(() -> EmptyAttachment.exception(EMPTY_ATTACHMENT_ENTRY.getMessage()));
                        deleteFileIgnoreExceptions(filePath);
                        writeBinaryFile(Path.of(fileName), screenshot.getRaw());
                    } else if (fileAttachmentEntry instanceof BigTextAttachmentEntry) {
                        String content = ((BigTextAttachmentEntry) fileAttachmentEntry).getContent().orElse("");
                        deleteFileIgnoreExceptions(filePath);
                        writeTextFile(filePath, content);
                    }
                    return entry.getName() + " " + filePath.toAbsolutePath().toString();
                }).collect(Collectors.joining("\n"));

        String stringAttachmentsDescription = attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof TextAttachmentEntry)
                .map(entry -> DELIMITER + entry.getName() + "\n" + DELIMITER + entry.getDescription())
                .collect(Collectors.joining("\n"));

        String mainAttachmentDescription = attachment.getMainAttachmentEntry()
                .map(entry -> DELIMITER + entry.getName() + "\n" + DELIMITER + entry.getDescription())
                .orElse("");

        if (isBlank(fileAttachmentsDescription) && isBlank(stringAttachmentsDescription) && isBlank(mainAttachmentDescription)) {
            return "";
        }

        return "Attachments:\n"
                + DELIMITER
                + (isBlank(fileAttachmentsDescription) ? "" : fileAttachmentsDescription + "\n")
                + (isBlank(stringAttachmentsDescription) ? "" : stringAttachmentsDescription + "\n")
                + (isBlank(mainAttachmentDescription) ? "" : mainAttachmentDescription + "\n");
    }

    protected String createId() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1, 999_999_999));
    }

}
