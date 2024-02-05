package io.perfeccionista.framework.attachment;

import io.perfeccionista.framework.exceptions.PreconditionViolation.PreconditionViolationException;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class AttachmentTest extends SimpleParallelTest {

    @Test
    void initializationSuccessTest() {
        Attachment attachment = Attachment.empty();
        assertNotNull(attachment.getAttachmentEntries());
        assertEquals(0L, attachment.getAttachmentEntries().count());
        assertEquals(Optional.empty(), attachment.getAttachmentEntry(AttachmentEntry.class, "name"));
    }

    @Test
    void notNullArgumentsTest() {
        Attachment attachment = Attachment.empty();
        assertThrows(PreconditionViolationException.class, () -> attachment.addLastAttachmentEntry(null));
        assertThrows(PreconditionViolationException.class, () -> attachment.getAttachmentEntriesByType(null));
        assertThrows(PreconditionViolationException.class, () -> attachment.getAttachmentEntry(null, "name"));
        assertThrows(PreconditionViolationException.class, () -> attachment.getAttachmentEntry(AttachmentEntry.class, null));
        assertThrows(PreconditionViolationException.class, () -> attachment.getAttachmentEntry(null, null));
    }

}
