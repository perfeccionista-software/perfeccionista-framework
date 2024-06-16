package io.perfeccionista.framework.exceptions.attachments;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

public class JsonAttachmentEntry extends FileAttachmentEntry<JsonNode> {

    protected JsonAttachmentEntry(String name, JsonNode content) {
        super(name, content);
    }

    public static JsonAttachmentEntry of(@NotNull String name, @Nullable JsonNode content) {
        return new JsonAttachmentEntry(name, content);
    }

    public static JsonAttachmentEntry of(@NotNull String name, @Nullable String content) {
        return new JsonAttachmentEntry(name, Objects.nonNull(content) ? parseJsonNode(content) : null);
    }

    @Override
    public String getDescription() {
        return this.getContent()
                .orElse(createObjectNode())
                .toPrettyString();
    }

    @Override
    public @NotNull String getFileExtension() {
        return "json";
    }

}
