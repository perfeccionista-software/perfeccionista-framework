package io.perfeccionista.framework.attachment;

import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import io.perfeccionista.framework.exceptions.impl.PreconditionViolationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.SimpleParallelTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class AttachmentEntryTest extends SimpleParallelTest {

    @Test
    void initializationFailedTest() {
        assertThrows(PreconditionViolationException.class, () -> new TestAttachmentEntry(null, null));
    }

        @Test
        void initializationSuccessTest() {
            Object object = new Object();

            TestAttachmentEntry attachmentEntry1 = new TestAttachmentEntry( "name", null);
            assertEquals("name", attachmentEntry1.getName());
            assertEquals(Optional.empty(), attachmentEntry1.getContent());

            TestAttachmentEntry attachmentEntry2 = new TestAttachmentEntry( "name", object);
            assertEquals("name", attachmentEntry2.getName());
            assertEquals(Optional.of(object), attachmentEntry2.getContent());
        }

    @Test
    void typifiedAttachmentEntryInitializationFailedTest() {
        assertThrows(PreconditionViolationException.class, () -> new TypifiedAttachmentEntry(null, null));
    }

        @Test
        void typifiedAttachmentEntryInitializationSuccessTest() {
            TypifiedAttachmentEntry attachmentEntry1 = new TypifiedAttachmentEntry("name", null);
            assertEquals(Optional.empty(), attachmentEntry1.getContent());
            String s = "String";
            TypifiedAttachmentEntry attachmentEntry2 = new TypifiedAttachmentEntry("name", s);
            assertEquals(Optional.of(s), attachmentEntry2.getContent());
        }

    private static class TestAttachmentEntry extends AttachmentEntry<Object> {

        public TestAttachmentEntry(@NotNull String entryName, @Nullable Object entryObject) {
            super(entryName, entryObject);
        }

        @Override
        public String getDescription() {
            return "Test attachment entry";
        }

    }

    private static class TypifiedAttachmentEntry extends AttachmentEntry<String> {

        public TypifiedAttachmentEntry(@NotNull String entryName, @Nullable String entryObject) {
            super(entryName, entryObject);
        }

        @Override
        public String getDescription() {
            return "Typified attachment entry";
        }

    }

}
