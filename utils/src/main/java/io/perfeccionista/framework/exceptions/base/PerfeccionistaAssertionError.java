package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import io.perfeccionista.framework.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

public class PerfeccionistaAssertionError extends AssertionError implements PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    private final LocalDateTime exceptionTimestamp;

    private Attachment attachment = null;
    private boolean processed = false;
    private boolean service = false;

    protected PerfeccionistaAssertionError(@NotNull String message) {
        super(message);
        exceptionTimestamp = LocalDateTime.now();
    }

    protected PerfeccionistaAssertionError(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        exceptionTimestamp = LocalDateTime.now();
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }

    @Override
    public PerfeccionistaAssertionError setProcessed(boolean processed) {
        this.processed = processed;
        return this;
    }

    @Override
    public boolean isService() {
        return service;
    }

    @Override
    public PerfeccionistaAssertionError setService(boolean service) {
        this.service = service;
        return this;
    }

    @Override
    public Optional<Attachment> getAttachment() {
        return Optional.ofNullable(attachment);
    }

    @Override
    public PerfeccionistaAssertionError setAttachment(@Nullable Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    @Override
    public PerfeccionistaAssertionError addFirstAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        if (getAttachment().isEmpty()) {
            this.attachment = Attachment.of();
        }
        this.attachment.addFirstAttachmentEntry(attachmentEntry);
        return this;
    }

    @Override
    public PerfeccionistaAssertionError addLastAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        if (getAttachment().isEmpty()) {
            this.attachment = Attachment.of();
        }
        this.attachment.addLastAttachmentEntry(attachmentEntry);
        return this;
    }

    @Override
    public PerfeccionistaAssertionError addSuppressedException(@NotNull Throwable throwable) {
        if (throwable instanceof PerfeccionistaRuntimeException) {
            PerfeccionistaRuntimeException exception = (PerfeccionistaRuntimeException) throwable;
            exception.getAttachment()
                    .ifPresent(value -> value.getAttachmentEntries().forEach(this::addLastAttachmentEntry));
            exception.setAttachment(null);
        }
        if (throwable instanceof PerfeccionistaAssertionError) {
            PerfeccionistaAssertionError assertionError = (PerfeccionistaAssertionError) throwable;
            assertionError.getAttachment()
                    .ifPresent(value -> value.getAttachmentEntries().forEach(this::addLastAttachmentEntry));
            assertionError.setAttachment(null);
        }
        this.addSuppressed(throwable);
        return this;
    }

    public LocalDateTime getExceptionTimestamp() {
        return exceptionTimestamp;
    }

    public String getAttachmentDescription() {
        if (null == attachment) {
            return "AssertionError has no attachment";
        }
        return "\n\nAssertionError attachments:\n\n" + attachment.getAttachmentEntries()
                .map(attachmentEntry -> ATTACHMENT_SPLITTER
                        + attachmentEntry.getName() + "\n"
                        + ATTACHMENT_SPLITTER
                        + attachmentEntry.getDescription() + "\n")
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        StringBuilder exceptionDescription = new StringBuilder();
        String attachmentDescription = getAttachmentDescription();
        if (StringUtils.isNotBlank(attachmentDescription)) {
            exceptionDescription.append(attachmentDescription).append("\n")
                    .append("Exception timestamp: ").append(getExceptionTimestamp().format(DateTimeFormatter.ISO_DATE_TIME)).append("\n\n");
        }
        exceptionDescription.append(super.toString());
        return exceptionDescription.toString();
    }

    @Override
    public String stacktraceToString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);
        return sw.toString();
    }

}
