package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.pagefactory.filter.MobileFilterBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MobileFilterBuilderDescriptionAttachmentEntry extends TextAttachmentEntry {

    protected MobileFilterBuilderDescriptionAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static MobileFilterBuilderDescriptionAttachmentEntry of(@Nullable MobileFilterBuilder<?, ?> filterBuilder) {
        return new MobileFilterBuilderDescriptionAttachmentEntry("Filter Description", Objects.isNull(filterBuilder) ? null : filterBuilder.toString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}

