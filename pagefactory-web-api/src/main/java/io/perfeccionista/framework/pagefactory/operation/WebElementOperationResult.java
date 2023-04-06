package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.SingleResultConversion;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_HASH_NOT_CALCULATED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_PROCESSING_RESULT_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.OPERATION_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementOperationResult<T> {

    protected WebExceptionMapper exceptionMapper;
    protected RuntimeException exception;

    protected final Map<String, Map<Integer, WebLocatorProcessingResult>> searchHistory;
    protected final Map<Integer, T> values;
    protected final String pageSource;

    protected WebElementOperationResult(Map<String, Map<Integer, WebLocatorProcessingResult>> searchHistory,
                                        Map<Integer, T> values,
                                        WebExceptionMapper exceptionMapper,
                                        RuntimeException exception,
                                        String pageSource) {
        this.searchHistory = searchHistory;
        this.values = values;
        this.exceptionMapper = exceptionMapper;
        this.exception = exception;
        this.pageSource = pageSource;
    }

    public static <T> WebElementOperationResult<T> of(@NotNull Map<String, Map<Integer, WebLocatorProcessingResult>> searchHistory,
                                                      @NotNull Map<Integer, T> values,
                                                      @NotNull String pageSource) {
        return new WebElementOperationResult<>(searchHistory, values, null, null, pageSource);
    }

    public static <T> WebElementOperationResult<T> of(@NotNull Map<String, Map<Integer, WebLocatorProcessingResult>> searchHistory,
                                                      @NotNull WebExceptionMapper exceptionMapper,
                                                      @NotNull RuntimeException exception,
                                                      @NotNull String pageSource) {
        return new WebElementOperationResult<>(searchHistory, new HashMap<>(), exceptionMapper, exception, pageSource);
    }

    public static <T> WebElementOperationResult<T> of(@NotNull WebExceptionMapper exceptionMapper,
                                                      @NotNull RuntimeException exception) {
        return new WebElementOperationResult<>(new HashMap<>(), new HashMap<>(), exceptionMapper, exception, "empty");
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public boolean isException() {
        return exception != null;
    }

    public WebElementOperationResult<T> ifSuccess(@NotNull Consumer<WebElementOperationResult<T>> action) {
        if (exception == null) {
            action.accept(this);
        }
        return this;
    }

    public WebElementOperationResult<T> ifException(@NotNull BiConsumer<? super WebExceptionMapper, ? super RuntimeException> action) {
        if (exception != null) {
            action.accept(exceptionMapper, exception);
        }
        return this;
    }

    public boolean hasResult() {
        return values.size() > 0;
    }

    public @NotNull T getNotNullResult() {
        if (values.size() > 1) {
            throw SingleResultConversion.exception(OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Values", valuesToJson()));
        }
        return values.entrySet().stream()
                .findFirst()
                .map(Entry::getValue)
                .orElseThrow(() -> SingleResultConversion.exception(OPERATION_RESULT_HAS_NO_VALUE.getMessage()));
    }

    public @Nullable T getResult() {
        if (values.size() > 1) {
            throw SingleResultConversion.exception(OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Values", valuesToJson()));
        }
        return values.entrySet().stream()
                .findFirst()
                .map(Entry::getValue)
                .orElse(null);
    }

    public Map<Integer, T> getResults() {
        return values;
    }

    public @Nullable RuntimeException getException() {
        return exception;
    }

    public @NotNull String getPageSource() {
        return pageSource;
    }

    public @NotNull String getRequiredHash(@NotNull String locatorId) {
        WebLocatorProcessingResult locatorProcessingResult = getWebLocatorProcessingResult(locatorId)
                .orElseThrow(() -> LocatorProcessing.exception(LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Locator ID", locatorId)));
        return locatorProcessingResult.getHash()
                .orElseThrow(() -> LocatorProcessing.exception(LOCATOR_HASH_NOT_CALCULATED.getMessage())
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Locator Processing Result", locatorProcessingResult.toJson())));
    }

    public Optional<WebLocatorProcessingResult> getWebLocatorProcessingResult(@NotNull String locatorId) {
        return getWebLocatorProcessingResult(locatorId, -1);
    }

    public Optional<WebLocatorProcessingResult> getWebLocatorProcessingResult(@NotNull String locatorId, int elementIndex) {
        return Optional.ofNullable(getWebLocatorProcessingResults(locatorId).get(elementIndex));
    }

    protected @NotNull Map<Integer, WebLocatorProcessingResult> getWebLocatorProcessingResults(String locatorId) {
        Map<Integer, WebLocatorProcessingResult> results = searchHistory.get(locatorId);
        if (results == null) {
            throw LocatorProcessing.exception(LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Locator ID", locatorId));
        }
        return results;
    }

    public JsonNode valuesToJson() {
        ObjectNode rootNode = createObjectNode();
        values.forEach((key, value) -> rootNode.putPOJO(String.valueOf(key), value));
        return rootNode;
    }

}
