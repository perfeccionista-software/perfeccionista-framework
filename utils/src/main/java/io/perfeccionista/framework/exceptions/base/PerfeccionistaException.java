package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.exceptions.attachments.Attachment;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

/**
 *
 * TODO: JavaDoc по каждому из параметров эксепшена - зачем он и как его использовать
 *
 * Нужно сначала создать исключений, чтобы корректно заполнилось время возникновения,
 * а потом заполнять его репорт.
 * Если мы перезаписываем эксепшн, то берем аттачмент из первоначального исключения и добавляем в него
 * необходимые энтри
 */
@API(status = EXPERIMENTAL, since = "1.0")
public interface PerfeccionistaException {

    String ATTACHMENT_SPLITTER = "----------------------------------------------------------------------------------------------------\n";

    boolean isProcessed();

    PerfeccionistaException setProcessed(boolean processed);

    boolean isService();

    PerfeccionistaException setService(boolean service);

    Optional<Attachment> getAttachment();

    PerfeccionistaException setAttachment(@Nullable Attachment attachment);

    PerfeccionistaException addFirstAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry);

    PerfeccionistaException addLastAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry);

    PerfeccionistaException addSuppressedException(@NotNull Throwable throwable);

    LocalDateTime getExceptionTimestamp();

    String getAttachmentDescription();

    String getLocalizedMessage();

    String getMessage();

    String stacktraceToString();

}
