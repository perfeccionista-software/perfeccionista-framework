package io.perfeccionista.framework.exceptions;

import io.perfeccionista.framework.AbstractParallelTest;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.SetAttachmentProcessor;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.processor.DefaultAttachmentProcessor;
import io.perfeccionista.framework.exceptions.impl.EmptyAttachmentException;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PerfeccionistaExtension.class)
@SetAttachmentProcessor(DefaultAttachmentProcessor.class)
public class AttachmentProcessorTest extends AbstractParallelTest {

    @Test
    void attachmentProcessorAnnotationTest() {
        Assertions.assertThrowsExactly(EmptyAttachmentException.class, () -> {
                    throw new EmptyAttachmentException("ExceptionMessage")
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Attachment name", "Attachment content"))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Attachment name 2", "Attachment content 2"))
                            .addLastAttachmentEntry(HtmlAttachmentEntry.of("MyHtml", "<html></html>"));
        });
    }

}
