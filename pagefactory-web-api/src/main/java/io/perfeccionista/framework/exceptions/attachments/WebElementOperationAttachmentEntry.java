package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WebElementOperationAttachmentEntry extends TextAttachmentEntry {

    protected WebElementOperationAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static WebElementOperationAttachmentEntry of(@Nullable WebElementOperation<?> operation) {
        return new WebElementOperationAttachmentEntry("Operation", Objects.isNull(operation) ? null : operation.toJson().toPrettyString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
