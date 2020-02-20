package io.perfeccionista.framework.exceptions.base;

import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.attachment.Attachment;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

final class PerfeccionistaExceptionTest extends SimpleParallelTest {

    @Test
    void initializationFailedTest() {
        assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaException(null));
        assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaException(null, new Exception()));
        assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaException("message", null));
    }

    @Test
    void notNullArgumentsTest() {
        PerfeccionistaException exception = new PerfeccionistaException("message");
        assertThrows(IllegalArgumentException.class, () -> exception.setType(null));
    }

    @Test
    void initializationSuccessTest() {
        PerfeccionistaException exception = new PerfeccionistaException("message");
        assertEquals(ExceptionType.EXCEPTION, exception.getType());
        assertEquals(Optional.empty(), exception.getAttachment());
        assertNotNull(exception.getExceptionTimestamp());
        assertFalse(exception.isProcessed());
        assertFalse(exception.isService());
    }

    @Test
    void setProcessedTest() {
        PerfeccionistaException exception = new PerfeccionistaException("message");
        assertFalse(exception.isProcessed());
        exception.setProcessed(true);
        assertTrue(exception.isProcessed());
    }

    @Test
    void setServiceTest() {
        PerfeccionistaException exception = new PerfeccionistaException("message");
        assertFalse(exception.isService());
        exception.setService(true);
        assertTrue(exception.isService());
    }

    @Test
    void setExceptionTypeTest() {
        PerfeccionistaException exception = new PerfeccionistaException("message");
        assertEquals(ExceptionType.EXCEPTION, exception.getType());
        exception.setType(ExceptionType.ASSERT);
        assertEquals(ExceptionType.ASSERT, exception.getType());
        exception.setType(ExceptionType.EXCEPTION);
        assertEquals(ExceptionType.EXCEPTION, exception.getType());
    }

    @Test
    void setAttachmentTest() {
        PerfeccionistaException exception = new PerfeccionistaException("message");
        assertEquals(Optional.empty(), exception.getAttachment());
        Attachment attachment = mock(Attachment.class);
        exception.setAttachment(attachment);
        assertEquals(Optional.of(attachment), exception.getAttachment());
    }

}
