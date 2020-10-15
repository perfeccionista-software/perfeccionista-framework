package io.perfeccionista.framework.attachment;

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
        Attachment attachment = Attachment.of();
        assertNotNull(attachment.getAttachmentEntries());
        assertEquals(0L, attachment.getAttachmentEntries().count());
        assertEquals(Optional.empty(), attachment.getAttachmentEntry(AttachmentEntry.class, "name"));
    }

    @Test
    void notNullArgumentsTest() {
        Attachment attachment = Attachment.of();
        assertThrows(IllegalArgumentException.class, () -> attachment.addLastAttachmentEntry(null));
        assertThrows(IllegalArgumentException.class, () -> attachment.getAttachmentEntriesByType(null));
        assertThrows(IllegalArgumentException.class, () -> attachment.getAttachmentEntry(null, "name"));
        assertThrows(IllegalArgumentException.class, () -> attachment.getAttachmentEntry(AttachmentEntry.class, null));
        assertThrows(IllegalArgumentException.class, () -> attachment.getAttachmentEntry(null, null));
    }

}
