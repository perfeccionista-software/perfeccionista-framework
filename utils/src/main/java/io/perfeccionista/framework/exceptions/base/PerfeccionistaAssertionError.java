package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.opentest4j.AssertionFailedError;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Optional;



// TODO: Разобраться с Expected/Actual (добавить методы и отрефакторить места создания), с Service (переименовать в Infrastracture?) + разобраться с ExceptionCollector
public class PerfeccionistaAssertionError extends AssertionFailedError implements PerfeccionistaException {

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
        if (!getAttachment().isPresent()) {
            this.attachment = Attachment.with();
        }
        this.attachment.addFirstAttachmentEntry(attachmentEntry);
        return this;
    }

    @Override
    public PerfeccionistaAssertionError addLastAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        if (!getAttachment().isPresent()) {
            this.attachment = Attachment.with();
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

    @Override
    public String stacktraceToString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);
        return sw.toString();
    }

}
