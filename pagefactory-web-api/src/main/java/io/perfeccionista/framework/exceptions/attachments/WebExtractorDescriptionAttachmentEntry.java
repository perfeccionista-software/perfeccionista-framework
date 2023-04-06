package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WebExtractorDescriptionAttachmentEntry extends TextAttachmentEntry {

    protected WebExtractorDescriptionAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static WebExtractorDescriptionAttachmentEntry of(@Nullable WebItemValueExtractor<?, ?> extractor) {
        return new WebExtractorDescriptionAttachmentEntry("Extractor Description", Objects.isNull(extractor) ? null : extractor.toString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}

