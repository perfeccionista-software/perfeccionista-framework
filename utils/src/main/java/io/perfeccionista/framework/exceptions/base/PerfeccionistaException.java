package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.attachment.AttachmentEntry;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.attachment.Attachment;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

/**
 * Base class for all {@link RuntimeException RuntimeExceptions} thrown by
 * Perfeccionista framework.<br/>
 * Base {@link ExceptionType} is {@link ExceptionType#EXCEPTION}, but if you need
 * check business logic you should use {@link ExceptionType#ASSERT}<br/>
 *
 * TODO: Завести на Артема задачи, которые позволят подерживать передачу ошибки с прикремпленным репортом
 * TODO: JavaDoc по каждому из параметров эксепшена - зачем он и как его использовать
 * TODO: Добавить ID выброса эксепшена и сравнивать ошибки по этому ID для Flaky.
 *
 * Нужно сначала создать исключений, чтобы корректно заполнилось время возникновения,
 * а потом заполнять его репорт.
 * Если мы перезаписываем эксепшн, то берем аттачмент из первоначального исключения и добавляем в него
 * необходимые энтри
 */
@API(status = EXPERIMENTAL, since = "1.0")
public class PerfeccionistaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected static final String ATTACHMENT_SPLITTER = "----------------------------------------------------------------------------------------------------\n";

    private final LocalDateTime exceptionTimestamp;

    private ExceptionType type = ExceptionType.EXCEPTION;
    private Attachment attachment = null;
    private boolean processed = false;
    private boolean service = false;

    public PerfeccionistaException(@NotNull String message) {
        super(message);
        exceptionTimestamp = LocalDateTime.now();
    }

    public PerfeccionistaException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
        exceptionTimestamp = LocalDateTime.now();
    }

    @NotNull
    public ExceptionType getType() {
        return type;
    }

    public PerfeccionistaException setType(@NotNull ExceptionType type) {
        this.type = type;
        return this;
    }

    public boolean isProcessed() {
        return processed;
    }

    public PerfeccionistaException setProcessed(boolean processed) {
        this.processed = processed;
        return this;
    }

    public boolean isService() {
        return service;
    }

    public PerfeccionistaException setService(boolean service) {
        this.service = service;
        return this;
    }

    public Optional<Attachment> getAttachment() {
        return Optional.ofNullable(attachment);
    }

    public PerfeccionistaException setAttachment(@Nullable Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public PerfeccionistaException addAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        if (getAttachment().isEmpty()) {
            this.attachment = Attachment.of();
        }
        this.attachment.addAttachmentEntry(attachmentEntry);
        return this;
    }

    public final LocalDateTime getExceptionTimestamp() {
        return exceptionTimestamp;
    }

    public String getAttachmentDescription() {
        if (null == attachment) {
            return "Exception has no attachment";
        }
        return "\n\nException attachments:\n\n" + attachment.getAttachmentEntries()
                .map(attachmentEntry -> ATTACHMENT_SPLITTER
                        + attachmentEntry.getName() + "\n"
                        + ATTACHMENT_SPLITTER
                        + attachmentEntry.getDescription() + "\n")
                .collect(Collectors.joining("\n"));
    }

}
