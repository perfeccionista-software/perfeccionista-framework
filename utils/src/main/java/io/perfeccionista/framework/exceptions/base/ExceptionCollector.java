package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.exceptions.attachments.BigTextAttachmentEntry;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

/**
 * TODO: JavaDoc
 * Класс можно экстендить и задавать реализацию через конфигурацию, чтобы было возможно менять алгоритм формирования сета эксепшенов
 */
public class ExceptionCollector {

    private final DateTimeFormatter formatter;
    private final List<PerfeccionistaException> exceptions = new ArrayList<>();

    private final Set<String> uniqueExceptionKeys = new HashSet<>();

    public ExceptionCollector(@NotNull PerfeccionistaException initialException) {
        formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        exceptions.add(initialException);
    }

    public ExceptionCollector(@NotNull PerfeccionistaException initialException, @NotNull DateTimeFormatter messageDateTimeFormatter) {
        formatter = messageDateTimeFormatter;
        exceptions.add(initialException);
    }

    /**
     * Добавляем экземпляр исключения для сравнения его с предыдущим используя выбранный SORT_MODE
     * @param exception не null
     */
    public void processException(@NotNull PerfeccionistaException exception) {
        uniqueExceptionKeys.add(exception.getClass().getCanonicalName() + ": " + exception.getLocalizedMessage());
        exceptions.add(exception);
    }

    /**
     * Если у нас попадались эксепшены одного типа, то просто его и выбрасываем
     */
    public void throwLastException() {
        PerfeccionistaException lastException = exceptions.get(exceptions.size() - 1);
        if (lastException instanceof PerfeccionistaAssertionError) {
            PerfeccionistaAssertionError assertionError = (PerfeccionistaAssertionError) lastException;
            if (uniqueExceptionKeys.size() == 1) {
                throw assertionError;
            }
            throw assertionError
                    .addLastAttachmentEntry(BigTextAttachmentEntry.of("All Exception Messages", generateExceptionSequenceMessage()));
        }
        if (lastException instanceof PerfeccionistaRuntimeException) {
            PerfeccionistaRuntimeException runtimeException = (PerfeccionistaRuntimeException) lastException;
            if (uniqueExceptionKeys.size() == 1) {
                throw runtimeException;
            }
            throw runtimeException
                    .addLastAttachmentEntry(BigTextAttachmentEntry.of("All Exception Messages", generateExceptionSequenceMessage()));
        }
    }

    /**
     * TODO: JavaDoc
     *  переделать на формирование сообщения их последнего эксепшена, а уникальный ключ
     * @return
     */
    @NotNull
    public String generateExceptionSequenceMessage() {
        if (exceptions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        List<String> exceptionDescriptions = new ArrayList<>();

        PerfeccionistaException firstException = exceptions.get(0);

        Class<? extends PerfeccionistaException> processedExceptionClass = firstException.getClass();
        String processedExceptionMessage = firstException.getMessage();
        String processedExceptionDescription = getExceptionDescription(firstException);

        int sameExceptionMessageCount = 1;
        LocalDateTime firstSameExceptionTimestamp = firstException.getExceptionTimestamp();
        LocalDateTime lastSameExceptionTimestamp = firstException.getExceptionTimestamp();

        for (int i = 1; i < exceptions.size(); i++) {
            PerfeccionistaException currentException = exceptions.get(i);

            // Если ошибки идентичны
            if (processedExceptionClass.equals(currentException.getClass())
                    && processedExceptionMessage.equals(currentException.getMessage())) {
                sameExceptionMessageCount++;
                lastSameExceptionTimestamp = currentException.getExceptionTimestamp();
                processedExceptionDescription = getExceptionDescription(currentException);

            // Если ошибки различаются
            } else {
                sb.append(format("%1$10s", sameExceptionMessageCount)).append(" ")
                        .append(firstSameExceptionTimestamp.format(formatter)).append(" - ")
                        .append(lastSameExceptionTimestamp.format(formatter)).append(" ")
                        .append(processedExceptionClass.getSimpleName()).append(": ")
                        .append(processedExceptionMessage).append("\n");

                exceptionDescriptions.add(processedExceptionDescription);

                processedExceptionClass = currentException.getClass();
                processedExceptionMessage = currentException.getMessage();
                sameExceptionMessageCount = 1;
                firstSameExceptionTimestamp = currentException.getExceptionTimestamp();
                lastSameExceptionTimestamp = currentException.getExceptionTimestamp();
                processedExceptionDescription = getExceptionDescription(currentException);
            }

        }

        sb.append(format("%1$10s", sameExceptionMessageCount)).append(" ")
                .append(firstSameExceptionTimestamp.format(formatter)).append(" - ")
                .append(lastSameExceptionTimestamp.format(formatter)).append(" ")
                .append(processedExceptionClass.getSimpleName()).append(": ")
                .append(processedExceptionMessage).append("\n\n");
        exceptionDescriptions.add(processedExceptionDescription);

        exceptionDescriptions.forEach(sb::append);

        return sb.toString();
    }

    protected String getExceptionDescription(PerfeccionistaException exception) {
        String exceptionID = exception.getClass().getCanonicalName() + ": " + exception.getMessage();
        int length = !exceptionID.contains("\n") ? exceptionID.length() : exceptionID.indexOf("\n");
        StringBuilder exceptionDescription = new StringBuilder()
                .append(getDelimiter(length)).append("\n")
                .append(exception.getClass().getCanonicalName()).append(": ").append(exception.getMessage())
                .append(exception.stacktraceToString()).append("\n")
                .append(getDelimiter(length));
        return exceptionDescription.toString();
    }

    protected String getDelimiter(int length) {
        int indent = length + 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("=");
        }
        return sb.append("\n").toString();
    }

}
