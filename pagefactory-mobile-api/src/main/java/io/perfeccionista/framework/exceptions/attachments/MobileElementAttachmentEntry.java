package io.perfeccionista.framework.exceptions.attachments;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;

public class MobileElementAttachmentEntry extends JsonAttachmentEntry {

    protected MobileElementAttachmentEntry(String name, JsonNode content) {
        super(name, content);
    }

    public static MobileElementAttachmentEntry of(@NotNull MobileChildElementBase content) {
        return new MobileElementAttachmentEntry("Element", content.toJson());
    }

    public static MobileElementAttachmentEntry of(@NotNull MobileParentElement content) {
        return new MobileElementAttachmentEntry("Element", content.toJson());
    }

}
