package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import io.perfeccionista.framework.exceptions.SingleResultConversion;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_HASH_NOT_CALCULATED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_PROCESSING_RESULT_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.OPERATION_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobilePageOperationResult<T> {

//    protected PerfeccionistaRuntimeException exception;
//    protected final Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory;
//    protected final Map<Integer, T> values;
//    protected final String pageSource;
//
//    protected MobilePageOperationResult(Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory,
//                                           Map<Integer, T> values,
//                                           PerfeccionistaRuntimeException exception,
//                                           String pageSource) {
//        this.searchHistory = searchHistory;
//        this.values = values;
//        this.exception = exception;
//        this.pageSource = pageSource;
//    }
//
//    public static <T> MobilePageOperationResult<T> of(Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory,
//                                                         Map<Integer, T> values,
//                                                         String pageSource) {
//        return new MobilePageOperationResult<>(searchHistory, values, null, pageSource);
//    }
//
//    public static <T> MobilePageOperationResult<T> of(Map<String, Map<Integer, MobileLocatorProcessingResult>> searchHistory,
//                                                         PerfeccionistaRuntimeException exception,
//                                                         String pageSource) {
//        return new MobilePageOperationResult<>(searchHistory, new HashMap<>(), exception, pageSource);
//    }
//
//    public boolean isSuccess() {
//        return exception == null;
//    }
//
//    public MobilePageOperationResult<T> ifSuccess(Consumer<MobilePageOperationResult<T>> action) {
//        if (exception == null) {
//            action.accept(this);
//        }
//        return this;
//    }
//
//    public MobilePageOperationResult<T> ifException(Consumer<? super PerfeccionistaRuntimeException> action) {
//        if (exception != null) {
//            action.accept(exception);
//        }
//        return this;
//    }
//
//    public T getResult() {
//        if (values.size() > 1) {
//            throw SingleResultConversion.exception(OPERATION_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
//                    .setProcessed(true)
//                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Values", valuesToJson()));
//        }
//        return values.entrySet().stream()
//                .findFirst()
//                .map(Entry::getValue)
//                .orElseThrow(() -> SingleResultConversion.exception(OPERATION_RESULT_HAS_NO_VALUE.getMessage()));
//    }
//
//    public Map<Integer, T> getResults() {
//        return values;
//    }
//
//    public @Nullable PerfeccionistaException getException() {
//        return exception;
//    }
//
//    public @NotNull String getRequiredHash(@NotNull String locatorId) {
//        MobileLocatorProcessingResult locatorProcessingResult = getJsMobileLocatorProcessingResult(locatorId)
//                .orElseThrow(() -> LocatorProcessing.exception(LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
//                        .addLastAttachmentEntry(StringAttachmentEntry.of("Locator ID", locatorId)));
//        return locatorProcessingResult.getHash()
//                .orElseThrow(() -> LocatorProcessing.exception(LOCATOR_HASH_NOT_CALCULATED.getMessage())
//                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Locator Processing Result", locatorProcessingResult.toJson())));
//    }
//
//    public Optional<MobileLocatorProcessingResult> getJsMobileLocatorProcessingResult(@NotNull String locatorId) {
//        return getJsMobileLocatorProcessingResult(locatorId, -1);
//    }
//
//    public Optional<MobileLocatorProcessingResult> getJsMobileLocatorProcessingResult(@NotNull String locatorId, int elementIndex) {
//        return Optional.ofNullable(getJsMobileLocatorProcessingResults(locatorId).get(elementIndex));
//    }
//
//    protected @NotNull Map<Integer, MobileLocatorProcessingResult> getJsMobileLocatorProcessingResults(String locatorId) {
//        Map<Integer, MobileLocatorProcessingResult> results = searchHistory.get(locatorId);
//        if (results == null) {
//            throw LocatorProcessing.exception(LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
//                    .addLastAttachmentEntry(StringAttachmentEntry.of("Locator ID", locatorId));
//        }
//        return results;
//    }
//
//    public String getPageSource() {
//        return pageSource;
//    }
//
//    public JsonNode valuesToJson() {
//        ObjectNode rootNode = createObjectNode();
//        values.forEach((key, value) -> rootNode.putPOJO(String.valueOf(key), value));
//        return rootNode;
//    }

}
