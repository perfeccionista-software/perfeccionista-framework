package io.perfeccionista.framework.exceptions.base;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ExceptionCollectorTest extends SimpleParallelTest {

    @Test
    void initializationFailedTest() {
        PerfeccionistaException initialException = new PerfeccionistaException("Initial message");
        assertThrows(IllegalArgumentException.class, () -> new ExceptionCollector(null));
        assertThrows(IllegalArgumentException.class, () -> new ExceptionCollector(null, null));
        assertThrows(IllegalArgumentException.class, () -> new ExceptionCollector(initialException, null));
        assertThrows(IllegalArgumentException.class, () -> new ExceptionCollector(null, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    void notNullArgumentsTest() {
        ExceptionCollector exceptionCollector = new ExceptionCollector(new PerfeccionistaException("Initial message"));
        assertThrows(IllegalArgumentException.class, () -> exceptionCollector.processException(null));
    }

    @Test
    void initializationFromMessageSuccessTest() {
        PerfeccionistaException initialException = new PerfeccionistaException("Initial message");
        ExceptionCollector exceptionCollector = new ExceptionCollector(initialException);
        String exceptionCollectorMessage = exceptionCollector.getExceptionSequence().getMessage();
        assertNotNull(exceptionCollectorMessage);
        assertTrue(exceptionCollectorMessage.contains("Initial message"));
    }

    @Test
    void initializationFromMessageAndFormatSuccessTest() {
        PerfeccionistaException initialException = new PerfeccionistaException("Initial message");
        ExceptionCollector exceptionCollector = new ExceptionCollector(initialException, DateTimeFormatter.ISO_TIME);
        String exceptionCollectorMessage = exceptionCollector.getExceptionSequence().getMessage();
        assertNotNull(exceptionCollectorMessage);
        assertTrue(exceptionCollectorMessage.contains("Initial message"));
    }

    @Test
    void processMessagesTest() {
        PerfeccionistaException initialException = new PerfeccionistaException("Initial message");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
        // 1-я строка
        ExceptionCollector exceptionCollector = new ExceptionCollector(initialException, formatter);
        // 2-я строка
        exceptionCollector.processException(new FirstTypeException("message"));
        exceptionCollector.processException(new FirstTypeException("message"));
        exceptionCollector.processException(new FirstTypeException("message"));
        // 3-я строка
        exceptionCollector.processException(new SecondTypeException("message"));
        // 4-я строка
        exceptionCollector.processException(new SecondTypeException("Another message"));
        exceptionCollector.processException(new SecondTypeException("Another message"));
        // 5-я строка
        exceptionCollector.processException(new FirstTypeException("Another message"));

        String[] exceptionMessageRows = exceptionCollector.getExceptionSequence().getMessage().split("\n");
        assertTrue(exceptionMessageRows[1].contains("PerfeccionistaException: Initial message"));
        assertTrue(exceptionMessageRows[2].contains("FirstTypeException: message"));
        assertTrue(exceptionMessageRows[3].contains("SecondTypeException: message"));
        assertTrue(exceptionMessageRows[4].contains("SecondTypeException: Another message"));
        assertTrue(exceptionMessageRows[5].contains("FirstTypeException: Another message"));
    }

    private static class FirstTypeException extends PerfeccionistaException {
        public FirstTypeException(@NotNull String message) {
            super(message);
        }
    }

    private static class SecondTypeException extends PerfeccionistaException {
        public SecondTypeException(@NotNull String message) {
            super(message);
        }
    }

}
