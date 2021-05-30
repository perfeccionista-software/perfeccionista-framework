package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import io.perfeccionista.framework.exceptions.SingleResultConversion;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
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

public class MobileElementOperationResult<T> {

    protected MobileExceptionMapper exceptionMapper;
    protected RuntimeException exception;

    protected final Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory;
    protected final Map<Integer, T> values;
    protected final String pageSource;

    protected MobileElementOperationResult(Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory,
                                        Map<Integer, T> values,
                                        MobileExceptionMapper exceptionMapper,
                                        RuntimeException exception,
                                        String pageSource) {
        this.searchHistory = searchHistory;
        this.values = values;
        this.exceptionMapper = exceptionMapper;
        this.exception = exception;
        this.pageSource = pageSource;
    }

    public static <T> MobileElementOperationResult<T> of(@NotNull Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory,
                                                         @NotNull Map<Integer, T> values,
                                                         @NotNull String pageSource) {
        return new MobileElementOperationResult<>(searchHistory, values, null, null, pageSource);
    }

    public static <T> MobileElementOperationResult<T> of(@NotNull Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory,
                                                         @NotNull MobileExceptionMapper exceptionMapper,
                                                         @NotNull RuntimeException exception,
                                                         @NotNull String pageSource) {
        return new MobileElementOperationResult<>(searchHistory, new HashMap<>(), exceptionMapper, exception, pageSource);
    }

    public static <T> MobileElementOperationResult<T> of(@NotNull MobileExceptionMapper exceptionMapper,
                                                         @NotNull RuntimeException exception) {
        return new MobileElementOperationResult<>(new HashMap<>(), new HashMap<>(), exceptionMapper, exception, "empty");
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public MobileElementOperationResult<T> ifSuccess(@NotNull Consumer<MobileElementOperationResult<T>> action) {
        if (exception == null) {
            action.accept(this);
        }
        return this;
    }

    public MobileElementOperationResult<T> ifException(@NotNull BiConsumer<? super MobileExceptionMapper, ? super RuntimeException> action) {
        if (exception != null) {
            action.accept(exceptionMapper, exception);
        }
        return this;
    }

    public T getResult() {
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

    public Map<Integer, T> getResults() {
        return values;
    }

    public @Nullable RuntimeException getException() {
        return exception;
    }

    public String getPageSource() {
        return pageSource;
    }

    public @NotNull String getRequiredHash(@NotNull String locatorId) {
        MobileLocatorProcessingResult locatorProcessingResult = getMobileLocatorProcessingResult(locatorId)
                .orElseThrow(() -> LocatorProcessing.exception(LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Locator ID", locatorId)));
        return locatorProcessingResult.getHash()
                .orElseThrow(() -> LocatorProcessing.exception(LOCATOR_HASH_NOT_CALCULATED.getMessage())
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Locator Processing Result", locatorProcessingResult.toJson())));
    }

    public Optional<MobileLocatorProcessingResult> getMobileLocatorProcessingResult(@NotNull String locatorId) {
        return getMobileLocatorProcessingResult(locatorId, -1);
    }

    public Optional<MobileLocatorProcessingResult> getMobileLocatorProcessingResult(@NotNull String locatorId, int elementIndex) {
        return Optional.ofNullable(getMobileLocatorProcessingResults(locatorId).get(elementIndex));
    }

    protected @NotNull Map<Integer, MobileLocatorProcessingResult> getMobileLocatorProcessingResults(String locatorId) {
        Map<Integer, MobileLocatorProcessingResult> results = searchHistory.get(locatorId);
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
