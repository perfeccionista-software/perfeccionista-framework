package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.pagefactory.extractor.MobileValueExtractor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MobileExtractorDescriptionAttachmentEntry extends TextAttachmentEntry {

    protected MobileExtractorDescriptionAttachmentEntry(String name, String content) {
        super(name, content);
    }

    public static MobileExtractorDescriptionAttachmentEntry of(@Nullable MobileValueExtractor<?, ?, ?> extractor) {
        return new MobileExtractorDescriptionAttachmentEntry("Extractor Description", Objects.isNull(extractor) ? null : extractor.toString());
    }

    @Override
    public String getDescription() {
        return this.getContent().orElse("empty");
    }

}
