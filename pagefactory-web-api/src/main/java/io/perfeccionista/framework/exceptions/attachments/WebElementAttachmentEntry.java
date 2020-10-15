package io.perfeccionista.framework.exceptions.attachments;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

public class WebElementAttachmentEntry extends JsonAttachmentEntry {

    protected WebElementAttachmentEntry(String name, JsonNode content) {
        super(name, content);
    }

    public static WebElementAttachmentEntry of(@NotNull WebChildElementBase content) {
        return new WebElementAttachmentEntry("Element", content.toJson());
    }

    public static WebElementAttachmentEntry of(@NotNull WebParentElement content) {
        return new WebElementAttachmentEntry("Element", content.toJson());
    }

}
