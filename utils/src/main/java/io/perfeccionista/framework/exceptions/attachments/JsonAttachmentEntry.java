package io.perfeccionista.framework.exceptions.attachments;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsonAttachmentEntry extends AttachmentEntry<JsonNode> {

    protected JsonAttachmentEntry(String name, JsonNode content) {
        super(name, content);
    }

    public static JsonAttachmentEntry of(@NotNull String name, @Nullable JsonNode content) {
        return new JsonAttachmentEntry(name, content);
    }

    @Override
    public String getDescription() {
        return this.getContent()
                .orElse(createObjectNode())
                .toPrettyString();
    }

}
