package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.SingleResultConversion;
import io.perfeccionista.framework.exceptions.WebLocatorProcessing;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.JS_OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.JS_OPERATION_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LOCATOR_HASH_NOT_CALCULATED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsOperationResult<T> {

    protected PerfeccionistaRuntimeException exception;
    protected final Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory;
    protected final Map<Integer, T> values;
    protected final String outerHtml;

    protected JsOperationResult(Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory,
                                Map<Integer, T> values,
                                PerfeccionistaRuntimeException exception,
                                String outerHtml) {
        this.searchHistory = searchHistory;
        this.values = values;
        this.exception = exception;
        this.outerHtml = outerHtml;
    }

    public static <T> JsOperationResult<T> of(Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory,
                                              Map<Integer, T> values,
                                              String outerHtml) {
        return new JsOperationResult<>(searchHistory, values, null, outerHtml);
    }

    public static <T> JsOperationResult<T> of(Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory,
                                              PerfeccionistaRuntimeException exception,
                                              String outerHtml) {
        return new JsOperationResult<>(searchHistory, new HashMap<>(), exception, outerHtml);
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public JsOperationResult<T> ifSuccess(Consumer<JsOperationResult<T>> action) {
        if (exception == null) {
            action.accept(this);
        }
        return this;
    }

    public JsOperationResult<T> ifException(Consumer<? super PerfeccionistaRuntimeException> action) {
        if (exception != null) {
            action.accept(exception);
        }
        return this;
    }

    public T getResult() {
        if (values.size() > 1) {
            throw SingleResultConversion.exception(JS_OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Values", valuesToJson()));
        }
        return values.entrySet().stream()
                .findFirst()
                .map(Entry::getValue)
                .orElseThrow(() -> SingleResultConversion.exception(JS_OPERATION_RESULT_HAS_NO_VALUE.getMessage()));
    }

    public Map<Integer, T> getResults() {
        return values;
    }

    public @Nullable PerfeccionistaException getException() {
        return exception;
    }

    public @NotNull String getOuterHtml() {
        return outerHtml;
    }

    public @NotNull String getRequiredHash(@NotNull String locatorId) {
        JsWebLocatorProcessingResult locatorProcessingResult = getJsWebLocatorProcessingResult(locatorId)
                .orElseThrow(() -> WebLocatorProcessing.exception(WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
                        .addLastAttachmentEntry(StringAttachmentEntry.of("Locator ID", locatorId)));
        return locatorProcessingResult.getHash()
                .orElseThrow(() -> WebLocatorProcessing.exception(WEB_LOCATOR_HASH_NOT_CALCULATED.getMessage())
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Locator Processing Result", locatorProcessingResult.toJson())));
    }

    public Optional<JsWebLocatorProcessingResult> getJsWebLocatorProcessingResult(@NotNull String locatorId) {
        return getJsWebLocatorProcessingResult(locatorId, -1);
    }

    public Optional<JsWebLocatorProcessingResult> getJsWebLocatorProcessingResult(@NotNull String locatorId, int elementIndex) {
        return Optional.ofNullable(getJsWebLocatorProcessingResults(locatorId).get(elementIndex));
    }

    protected @NotNull Map<Integer, JsWebLocatorProcessingResult> getJsWebLocatorProcessingResults(String locatorId) {
        Map<Integer, JsWebLocatorProcessingResult> results = searchHistory.get(locatorId);
        if (results == null) {
            throw WebLocatorProcessing.exception(WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Locator ID", locatorId));
        }
        return results;
    }

    public JsonNode valuesToJson() {
        ObjectNode rootNode = createObjectNode();
        values.forEach((key, value) -> rootNode.putPOJO(String.valueOf(key), value));
        return rootNode;
    }

}
