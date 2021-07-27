package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MobileElementOperationAttachmentEntry extends TextAttachmentEntry {

    protected MobileElementOperationAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static MobileElementOperationAttachmentEntry of(@Nullable MobileElementOperation<?> operation) {
        return new MobileElementOperationAttachmentEntry("Operation", Objects.isNull(operation) ? null : operation.toJson().toPrettyString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
