package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.AttachmentProcessor;
import io.perfeccionista.framework.exceptions.attachments.DefaultAttachmentProcessor;
import io.perfeccionista.framework.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class PerfeccionistaRuntimeException extends RuntimeException implements PerfeccionistaException {

    private static final long serialVersionUID = 1L;

    private final LocalDateTime exceptionTimestamp;

    private AttachmentProcessor processor = new DefaultAttachmentProcessor();
    private Attachment attachment = null;
    private boolean processed = false;
    private boolean service = false;

    protected PerfeccionistaRuntimeException(@NotNull String message) {
        super(message);
        exceptionTimestamp = LocalDateTime.now();
    }

    protected PerfeccionistaRuntimeException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        exceptionTimestamp = LocalDateTime.now();
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }

    @Override
    public PerfeccionistaRuntimeException setProcessed(boolean processed) {
        this.processed = processed;
        return this;
    }

    @Override
    public boolean isService() {
        return service;
    }

    @Override
    public PerfeccionistaRuntimeException setService(boolean service) {
        this.service = service;
        return this;
    }

    @Override
    public Optional<Attachment> getAttachment() {
        return Optional.ofNullable(attachment);
    }

    @Override
    public PerfeccionistaRuntimeException setAttachment(@Nullable Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    @Override
    public PerfeccionistaRuntimeException addFirstAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        if (!getAttachment().isPresent()) {
            this.attachment = Attachment.with();
        }
        this.attachment.addFirstAttachmentEntry(attachmentEntry);
        return this;
    }

    @Override
    public PerfeccionistaRuntimeException addLastAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        if (!getAttachment().isPresent()) {
            this.attachment = Attachment.with();
        }
        this.attachment.addLastAttachmentEntry(attachmentEntry);
        return this;
    }

    @Override
    public PerfeccionistaRuntimeException addSuppressedException(@NotNull Throwable throwable) {
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

    @Override
    public PerfeccionistaRuntimeException setAttachmentProcessor(@NotNull AttachmentProcessor processor) {
        this.processor = processor;
        return this;
    }

    public LocalDateTime getExceptionTimestamp() {
        return exceptionTimestamp;
    }

    public String getAttachmentDescription() {
        return Objects.nonNull(attachment)
                ? processor.processAttachment(attachment)
                : "";
    }

    @Override
    public String toString() {
        StringBuilder exceptionDescription = new StringBuilder();
        String attachmentDescription = getAttachmentDescription();
        if (StringUtils.isNotBlank(attachmentDescription)) {
            exceptionDescription.append(attachmentDescription)
                    .append(processor.getDelimiter())
                    .append("Exception timestamp: ").append(getExceptionTimestamp().format(DateTimeFormatter.ISO_DATE_TIME)).append("\n")
                    .append(processor.getDelimiter())
                    .append("\n");
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
