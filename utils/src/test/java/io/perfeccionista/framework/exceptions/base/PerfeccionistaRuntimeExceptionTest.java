package io.perfeccionista.framework.exceptions.base;

import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.exceptions.attachments.Attachment;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class PerfeccionistaRuntimeExceptionTest extends SimpleParallelTest {

    @Test
    void initializationFailedTest() {
        assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaRuntimeException(null));
        assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaRuntimeException(null, new Exception()));
        assertThrows(IllegalArgumentException.class, () -> new PerfeccionistaRuntimeException("message", null));
    }

    @Test
    void initializationSuccessTest() {
        PerfeccionistaException exception = new PerfeccionistaRuntimeException("message");
        assertEquals(Optional.empty(), exception.getAttachment());
        assertNotNull(exception.getExceptionTimestamp());
        assertFalse(exception.isProcessed());
        assertFalse(exception.isService());
    }

    @Test
    void setProcessedTest() {
        PerfeccionistaException exception = new PerfeccionistaRuntimeException("message");
        assertFalse(exception.isProcessed());
        exception.setProcessed(true);
        assertTrue(exception.isProcessed());
    }

    @Test
    void setServiceTest() {
        PerfeccionistaException exception = new PerfeccionistaRuntimeException("message");
        assertFalse(exception.isService());
        exception.setService(true);
        assertTrue(exception.isService());
    }

    @Test
    void setAttachmentTest() {
        PerfeccionistaException exception = new PerfeccionistaRuntimeException("message");
        assertEquals(Optional.empty(), exception.getAttachment());
        Attachment attachment = mock(Attachment.class);
        exception.setAttachment(attachment);
        assertEquals(Optional.of(attachment), exception.getAttachment());
    }

}
