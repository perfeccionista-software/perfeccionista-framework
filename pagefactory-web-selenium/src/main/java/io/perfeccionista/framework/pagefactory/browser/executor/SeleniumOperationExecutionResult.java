package io.perfeccionista.framework.pagefactory.browser.executor;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.pagefactory.operation.JsWebLocatorProcessingResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

class SeleniumOperationExecutionResult<T> {

    private final Function<Object, T> converter;
    private final JsonNode searchHistoryNode;
    private final Map<Integer, T> values;

    private JsonNode errorNode = null;
    private JsonNode logsNode = null;
    private String outerHtml = "empty";

    private SeleniumOperationExecutionResult(Object result, Function<Object, T> converter) {
        this.converter = converter;
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

    static <T> SeleniumOperationExecutionResult<T> of(Object result, Function<Object, T> converter) {
        return new SeleniumOperationExecutionResult<>(result, converter);
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

    public PerfeccionistaRuntimeException getException(@NotNull ExceptionMapper exceptionMapper) {
        return buildException(exceptionMapper);
    }

    public JsOperationResult<T> getSuccessfulOperationResult() {
        return JsOperationResult.of(extractSearchHistory(searchHistoryNode), values, outerHtml);
    }

    public JsOperationResult<T> getUnsuccessfulOperationResult(ExceptionMapper exceptionMapper) {
        PerfeccionistaRuntimeException exception = getException(exceptionMapper);
        if (withLogs()) {
            exception.addLastAttachmentEntry(JsonAttachmentEntry.of("JavaScript logs", getLogsNode()));
        }
        return JsOperationResult.of(extractSearchHistory(searchHistoryNode), exception, outerHtml);
    }

    protected Map<String, Map<Integer, JsWebLocatorProcessingResult>> extractSearchHistory(JsonNode searchHistoryNode) {
        Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory = new HashMap<>();
        for (JsonNode searchHistoryEntry : searchHistoryNode) {
            String locatorId = searchHistoryEntry.get("locator").get("locatorId").asText();
            Map<Integer, JsWebLocatorProcessingResult> locatorResultsMap = new HashMap<>();
            for (JsonNode locatorResultEntry : searchHistoryEntry.get("result")) {
                int index = locatorResultEntry.has("index") ? locatorResultEntry.get("index").asInt() : -1;
                boolean found = locatorResultEntry.get("found").asBoolean();
                String hash = locatorResultEntry.has("hash") ? locatorResultEntry.get("hash").asText() : null;
                Boolean hashCorrect = locatorResultEntry.has("hashCorrect") ? locatorResultEntry.get("hashCorrect").asBoolean() : null;
                locatorResultsMap.put(index, JsWebLocatorProcessingResult.of(index, found, hash, hashCorrect));
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
            results.put(index, value == null ? null : converter.apply(value));
        }
        return results;
    }

    protected PerfeccionistaRuntimeException buildException(@NotNull ExceptionMapper exceptionMapper) {
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