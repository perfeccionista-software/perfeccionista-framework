package io.perfeccionista.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.JsonParseException;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.JSON_OBJECT_PARSE_ERROR;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.JSON_STRING_PARSE_ERROR;

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    // From documentation: Mapper instances are fully thread-safe
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {}

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static JsonNode parseJsonNode(String content) {
        try {
            return objectMapper.readTree(content);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(JSON_STRING_PARSE_ERROR.getMessage())
                    .setAttachment(Attachment.of(StringAttachmentEntry.of("Json string", content)));
        }
    }

    public static String toPrettyJson(JsonNode node) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(JSON_OBJECT_PARSE_ERROR.getMessage())
                    .setAttachment(Attachment.of(StringAttachmentEntry.of("Json object", node.asText())));
        }
    }


}
