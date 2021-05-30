package io.perfeccionista.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.JsonParse;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.JSON_OBJECT_PARSE_ERROR;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.JSON_STRING_PARSE_ERROR;

public class JsonUtils {

    // Documentation said: Mapper instances are fully thread-safe
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static @NotNull ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static @NotNull JsonNode parseJsonNode(@NotNull String content) {
        try {
            return objectMapper.readTree(content);
        } catch (JsonProcessingException e) {
            throw JsonParse.exception(JSON_STRING_PARSE_ERROR.getMessage())
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Json string", content));
        }
    }

    public static @NotNull String toPrettyJson(@NotNull JsonNode node) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw JsonParse.exception(JSON_OBJECT_PARSE_ERROR.getMessage())
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Json object", node));
        }
    }


}
