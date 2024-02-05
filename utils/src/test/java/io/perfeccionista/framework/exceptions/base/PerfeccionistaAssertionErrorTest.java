package io.perfeccionista.framework.exceptions.base;

import io.perfeccionista.framework.SimpleParallelTest;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class PerfeccionistaAssertionErrorTest extends SimpleParallelTest {

    @Test
    void initializationSuccessTest() {
        PerfeccionistaException exception = new PerfeccionistaAssertionError("message");
        assertEquals(Optional.empty(), exception.getAttachment());
        assertNotNull(exception.getExceptionTimestamp());
        assertFalse(exception.isProcessed());
        assertFalse(exception.isService());
    }

    @Test
    void setProcessedTest() {
        PerfeccionistaException exception = new PerfeccionistaAssertionError("message");
        assertFalse(exception.isProcessed());
        exception.setProcessed(true);
        assertTrue(exception.isProcessed());
    }

    @Test
    void setServiceTest() {
        PerfeccionistaException exception = new PerfeccionistaAssertionError("message");
        assertFalse(exception.isService());
        exception.setService(true);
        assertTrue(exception.isService());
    }

    @Test
    void setAttachmentTest() {
        PerfeccionistaException exception = new PerfeccionistaAssertionError("message");
        assertEquals(Optional.empty(), exception.getAttachment());
        Attachment attachment = mock(Attachment.class);
        exception.setAttachment(attachment);
        assertEquals(Optional.of(attachment), exception.getAttachment());
    }

}
