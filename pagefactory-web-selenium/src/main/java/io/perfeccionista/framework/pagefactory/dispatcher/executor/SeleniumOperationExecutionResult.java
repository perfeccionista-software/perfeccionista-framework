package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.WebLocatorProcessingResult;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

class SeleniumOperationExecutionResult<T> {

    private final EndpointHandler<T> endpointHandler;
    private final JsonNode searchHistoryNode;
    private final Map<Integer, T> values;

    private JsonNode errorNode = null;
    private JsonNode logsNode = null;
    private String outerHtml = "empty";

    private SeleniumOperationExecutionResult(Object result, @NotNull EndpointHandler<T> endpointHandler) {
        this.endpointHandler = endpointHandler;
        Map<String, Object> resultMap = (Map<String, Object>) result;
        this.searchHistoryNode = parseJsonNode(resultMap.get("searchHistory").toString());
        if (resultMap.containsKey("error")) {
            this.errorNode = parseJsonNode(resultMap.get("error").toString());
        }
        if (resultMap.containsKey("logs")) {
            this.logsNode = parseJsonNode(resultMap.get("logs").toString());
        }
        if (resultMap.containsKey("outerHtml")) {
            this.outerHtml = resultMap.get("outerHtml").toString();
        }
        this.values = extractValues(resultMap.get("values"));
    }

    static <T> SeleniumOperationExecutionResult<T> of(Object result, @NotNull EndpointHandler<T> endpointHandler) {
        return new SeleniumOperationExecutionResult<>(result, endpointHandler);
    }

    public boolean withLogs() {
        return this.logsNode != null;
    }

    public String getLogs() {
        return logsNode == null ? "" : logsNode.toPrettyString();
    }

    public JsonNode getLogsNode() {
        return logsNode;
    }

    public boolean withException() {
        return this.errorNode != null;
    }

    public PerfeccionistaRuntimeException getException(@NotNull WebExceptionMapper exceptionMapper) {
        return buildException(exceptionMapper);
    }

    public WebElementOperationResult<T> getSuccessfulOperationResult() {
        return WebElementOperationResult.of(extractSearchHistory(searchHistoryNode), values, outerHtml);
    }

    public WebElementOperationResult<T> getUnsuccessfulOperationResult(WebExceptionMapper exceptionMapper) {
        PerfeccionistaRuntimeException exception = getException(exceptionMapper);
        if (withLogs()) {
            exception.addLastAttachmentEntry(JsonAttachmentEntry.of("JavaScript logs", getLogsNode()));
        }
        return WebElementOperationResult.of(extractSearchHistory(searchHistoryNode), exceptionMapper, exception, outerHtml);
    }

    protected Map<String, Map<Integer, WebLocatorProcessingResult>> extractSearchHistory(JsonNode searchHistoryNode) {
        Map<String, Map<Integer, WebLocatorProcessingResult>> searchHistory = new HashMap<>();
        for (JsonNode searchHistoryEntry : searchHistoryNode) {
            String locatorId = searchHistoryEntry.get("locator").get("locatorId").asText();
            Map<Integer, WebLocatorProcessingResult> locatorResultsMap = new HashMap<>();
            for (JsonNode locatorResultEntry : searchHistoryEntry.get("result")) {
                int index = locatorResultEntry.has("index") ? locatorResultEntry.get("index").asInt() : -1;
                boolean found = locatorResultEntry.get("found").asBoolean();
                String hash = locatorResultEntry.has("hash") ? locatorResultEntry.get("hash").asText() : null;
                Boolean hashCorrect = locatorResultEntry.has("hashCorrect") ? locatorResultEntry.get("hashCorrect").asBoolean() : null;
                locatorResultsMap.put(index, WebLocatorProcessingResult.of(index, found, hash, hashCorrect));
            }
            searchHistory.put(locatorId, locatorResultsMap);
        }
        return searchHistory;
    }

    protected Map<Integer, T> extractValues(Object valuesObject) {
        Map<Integer, T> results = new HashMap<>();
        ArrayList<Map<String, Object>> values = (ArrayList<Map<String, Object>>) valuesObject;
        for (Map<String, Object> valueEntry : values) {
            Object value = valueEntry.get("value");
            Integer index = -1;
            if (valueEntry.containsKey("index")) {
                index = ((Long) valueEntry.get("index")).intValue();
            }
            results.put(index, value == null ? null : endpointHandler.handle(value));
        }
        return results;
    }

    protected PerfeccionistaRuntimeException buildException(@NotNull WebExceptionMapper exceptionMapper) {
        String errorName = errorNode.get("name").asText();
        String errorMessage = errorNode.get("message").asText();
        PerfeccionistaRuntimeException exception = exceptionMapper.createByName(errorName, errorMessage)
                .addLastAttachmentEntry(StringAttachmentEntry.of("JavaScript error stacktrace", errorNode.get("stackTrace").asText()));
        for (JsonNode errorAttachmentEntry : errorNode.get("attachments")) {
            exception.addLastAttachmentEntry(resolveErrorAttachment(errorAttachmentEntry));
        }
        return exception;
    }

    protected AttachmentEntry<?> resolveErrorAttachment(JsonNode errorAttachmentEntry) {
        String attachmentType = errorAttachmentEntry.get("type").asText();
        if ("json".equals(attachmentType)) {
            return JsonAttachmentEntry.of(errorAttachmentEntry.get("name").asText(), errorAttachmentEntry.get("content"));
        } else if ("text/html".equals(attachmentType)) {
            return HtmlAttachmentEntry.of(errorAttachmentEntry.get("name").asText(), errorAttachmentEntry.get("content").asText());
        }
        return StringAttachmentEntry.of(errorAttachmentEntry.get("name").asText(), errorAttachmentEntry.get("content").asText());
    }

}
