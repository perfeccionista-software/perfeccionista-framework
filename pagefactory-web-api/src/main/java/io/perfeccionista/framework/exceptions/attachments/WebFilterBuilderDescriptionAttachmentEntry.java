package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WebFilterBuilderDescriptionAttachmentEntry extends TextAttachmentEntry {

    protected WebFilterBuilderDescriptionAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static WebFilterBuilderDescriptionAttachmentEntry of(@Nullable WebListFilterBuilder<?> filterBuilder) {
        return new WebFilterBuilderDescriptionAttachmentEntry("Filter Description", Objects.isNull(filterBuilder) ? null : filterBuilder.toString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
