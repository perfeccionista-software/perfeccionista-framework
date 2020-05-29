package io.perfeccionista.framework.exceptions.base;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

/**
 * TODO: JavaDoc
 * Класс можно экстендить и задавать реализацию через конфигурацию, чтобы было возможно менять алгоритм формирования сета эксепшенов
 */
public class ExceptionCollector {

    private final DateTimeFormatter formatter;
    private final Deque<PerfeccionistaException> exceptions = new ArrayDeque<>();

    public ExceptionCollector(@NotNull PerfeccionistaException initialException) {
        formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        exceptions.addFirst(initialException);
    }

    public ExceptionCollector(@NotNull PerfeccionistaException initialException, @NotNull DateTimeFormatter messageDateTimeFormatter) {
        formatter = messageDateTimeFormatter;
        exceptions.addFirst(initialException);
    }

    /**
     * Добавляем экземпляр исключения для сравнения его с предыдущим используя выбранный SORT_MODE
     * @param exception не null
     */
    public void processException(@NotNull PerfeccionistaException exception) {
        exceptions.addLast(exception);
    }

    /**
     * Генерируем необходимое исключение исходя их количества уникальных исключений
     * @return
     */
    @NotNull
    public PerfeccionistaException getException() {
        Set<String> uniqueExceptionKeys = new HashSet<>();
        for (PerfeccionistaException exception : exceptions) {
            uniqueExceptionKeys.add(exception.getClass().getCanonicalName() + ": " + exception.getLocalizedMessage());
        }
        if (uniqueExceptionKeys.size() == 1) {
            return exceptions.getLast();
        }
        return new PerfeccionistaExceptionSequence(exceptions, generateExceptionSequenceMessage(exceptions));
    }

    /**
     * TODO: JavaDoc
     *  переделать на формирование сообщения их последнего эксепшена, а уникальный ключ
     * @param exceptions
     * @return
     */
    @NotNull
    protected String generateExceptionSequenceMessage(@NotNull Deque<PerfeccionistaException> exceptions) {
        StringBuilder sb = new StringBuilder("PerfeccionistaExceptionSequence:\n");

        PerfeccionistaException firstException = exceptions.removeFirst();

        Class<? extends PerfeccionistaException> exceptionClass = firstException.getClass();
        String lastExceptionMessage = firstException.getMessage();
        int sameExceptionMessageCount = 1;
        LocalDateTime firstSameExceptionTimestamp = firstException.getExceptionTimestamp();
        LocalDateTime lastSameExceptionTimestamp = firstException.getExceptionTimestamp();

        for (PerfeccionistaException exception : exceptions) {
            if (exceptionClass.equals(exception.getClass()) && lastExceptionMessage.equals(exception.getMessage())) {
                sameExceptionMessageCount++;
                lastSameExceptionTimestamp = exception.getExceptionTimestamp();
            } else {
                sb.append(format("%1$10s", sameExceptionMessageCount)).append(" ")
                        .append(firstSameExceptionTimestamp.format(formatter)).append(" - ")
                        .append(lastSameExceptionTimestamp.format(formatter)).append(" ")
                        .append(exceptionClass.getSimpleName()).append(": ")
                        .append(lastExceptionMessage).append("\n");
                exceptionClass = exception.getClass();
                lastExceptionMessage = exception.getMessage();
                sameExceptionMessageCount = 1;
                firstSameExceptionTimestamp = exception.getExceptionTimestamp();
                lastSameExceptionTimestamp = exception.getExceptionTimestamp();
            }
        }

        sb.append(format("%1$10s", sameExceptionMessageCount)).append(" ")
                .append(firstSameExceptionTimestamp.format(formatter)).append(" - ")
                .append(lastSameExceptionTimestamp.format(formatter)).append(" ")
                .append(exceptionClass.getSimpleName()).append(": ")
                .append(lastExceptionMessage).append("\n");

        return sb.toString();
    }

}
