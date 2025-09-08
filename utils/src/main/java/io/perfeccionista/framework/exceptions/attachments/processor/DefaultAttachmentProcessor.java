package io.perfeccionista.framework.exceptions.attachments.processor;

import io.perfeccionista.framework.exceptions.EmptyAttachment;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.BigTextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.FileAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ScreenshotAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.util.Properties;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_ATTACHMENT_ENTRY;
import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;
import static io.perfeccionista.framework.utils.FileUtils.writeBinaryFile;
import static io.perfeccionista.framework.utils.FileUtils.writeTextFile;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.StringUtils.isBlank;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;

public class DefaultAttachmentProcessor implements AttachmentProcessor {

    protected static final String BUILD_SYSTEM = "perfeccionista.build.attachment.dir";
    protected static Properties perfeccionistaProperties;
    protected static Properties systemProperties;

    public static final Logger log = LoggerFactory.getLogger(DefaultAttachmentProcessor.class);
    public static final DateTimeFormatter ID_FORMAT;
    public static final DateTimeFormatter ALL_OS_ISO_LOCAL_TIME;
    
    static {
        ALL_OS_ISO_LOCAL_TIME = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral('-')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral('-')
                .appendValue(SECOND_OF_MINUTE, 2)
                .optionalStart()
                .appendFraction(NANO_OF_SECOND, 0, 9, true)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);

        ID_FORMAT = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendValue(MONTH_OF_YEAR, 2)
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('-')
                .append(ALL_OS_ISO_LOCAL_TIME)
                .toFormatter();
    }
    
    @Override
    public String processAttachment(@NotNull Attachment attachment) {
        String fileAttachmentsDescription = attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof FileAttachmentEntry<?>)
                .map(entry -> {
                    FileAttachmentEntry<?> fileAttachmentEntry = (FileAttachmentEntry<?>) entry;
                    String fileName = createId() + "_" + entry.getName() + "." + fileAttachmentEntry.getFileExtension();
                    Path filePath = Paths.get(getAttachmentDir() + File.separator + fileName);
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
                        writeBinaryFile(filePath, screenshot.getRaw());
                    } else if (fileAttachmentEntry instanceof BigTextAttachmentEntry) {
                        String content = ((BigTextAttachmentEntry) fileAttachmentEntry).getContent().orElse("");
                        deleteFileIgnoreExceptions(filePath);
                        writeTextFile(filePath, content);
                    }
                    return getDelimiter(true) + entry.getName() + " " + filePath.toAbsolutePath();
                }).collect(Collectors.joining("\n"));

        String stringAttachmentsDescription = attachment.getAttachmentEntries()
                .filter(entry -> entry instanceof TextAttachmentEntry)
                .map(entry -> getDelimiter(true) + "[" + entry.getName() + "]" + "\n" + entry.getDescription())
                .collect(Collectors.joining("\n"));

        if (isBlank(fileAttachmentsDescription) && isBlank(stringAttachmentsDescription)) {
            return "";
        }

        String attachmentDescription = "Attachments:\n"
                + (isBlank(fileAttachmentsDescription) ? "" : fileAttachmentsDescription + "\n")
                + (isBlank(stringAttachmentsDescription) ? "" : stringAttachmentsDescription + "\n")
                + getDelimiter(false);

        log.info(attachmentDescription);

        return attachmentDescription;
    }

    protected String createId() {
        return LocalDateTime.now().format(ID_FORMAT);
    }

    protected String getAttachmentDir(){
        perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath("perfeccionista.properties")
                .orElse(new Properties());
        systemProperties = System.getProperties();

        if (systemProperties.containsKey(BUILD_SYSTEM)) {
            return systemProperties.getProperty(BUILD_SYSTEM);
        }

        if (perfeccionistaProperties.containsKey(BUILD_SYSTEM)) {
            return perfeccionistaProperties.getProperty(BUILD_SYSTEM);
        }
        
        return "attachments";
    }
}