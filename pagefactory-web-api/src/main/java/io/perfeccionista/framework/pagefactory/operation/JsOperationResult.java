package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebLocatorProcessingException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebMultipleResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND;

public class JsOperationResult<T> {

    protected PerfeccionistaException exception;
    protected Class<? extends MultipleResult> resultTypeImplementation;
    protected final Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory;
    protected final Map<Integer, T> values;
    protected final String outerHtml;

    protected JsOperationResult(Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory,
                                Map<Integer, T> values,
                                PerfeccionistaException exception,
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
                                              PerfeccionistaException exception,
                                              String outerHtml) {
        return new JsOperationResult<>(searchHistory, new HashMap<>(), exception, outerHtml);
    }

    public JsOperationResult<T> withResultTypeImplementation(Class<? extends MultipleResult> resultTypeImplementation) {
        this.resultTypeImplementation = resultTypeImplementation;
        return this;
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public void ifSuccess(Consumer<MultipleResult<T>> action) {
        if (exception == null) {
            action.accept(multipleResult());
        }
    }

    public void ifException(Consumer<? super PerfeccionistaException> action) {
        if (exception != null) {
            action.accept(exception);
        }
    }

    public @NotNull SingleResult<T> singleResult() {
        return multipleResult()
                .singleResult();
    }

    public @NotNull MultipleResult<T> multipleResult() {
        return new WebMultipleResult<>(values);
    }

    public @Nullable PerfeccionistaException getException() {
        return exception;
    }

    public @NotNull String getOuterHtml() {
        return outerHtml;
    }

    public Optional<JsWebLocatorProcessingResult> getJsWebLocatorProcessingResult(@NotNull String locatorId) {
        return getJsWebLocatorProcessingResult(locatorId, -1);
    }

    public Optional<JsWebLocatorProcessingResult> getJsWebLocatorProcessingResult(@NotNull String locatorId, int elementIndex) {
        return Optional.ofNullable(getResults(locatorId).get(elementIndex));
    }

    protected @NotNull Map<Integer, JsWebLocatorProcessingResult> getResults(String locatorId) {
        Map<Integer, JsWebLocatorProcessingResult> results = searchHistory.get(locatorId);
        if (results == null) {
            throw new WebLocatorProcessingException(WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND.getMessage())
                    .addAttachmentEntry(StringAttachmentEntry.of("locatorId", locatorId));
        }
        return results;
    }

}
