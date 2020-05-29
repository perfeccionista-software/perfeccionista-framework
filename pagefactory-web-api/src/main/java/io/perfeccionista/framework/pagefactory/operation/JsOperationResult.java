package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebLocatorProcessingException;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebMultipleResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND;

public class JsOperationResult<T> {

    protected Class<? extends MultipleResult> resultImplementation;
    protected final Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory;
    protected final Map<Integer, T> values;

    protected JsOperationResult(Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory, Map<Integer, T> values) {
        this.searchHistory = searchHistory;
        this.values = values;
    }

    public static <T> JsOperationResult<T> of(Map<String, Map<Integer, JsWebLocatorProcessingResult>> searchHistory, Map<Integer, T> values) {
        return new JsOperationResult<>(searchHistory, values);
    }

    public JsOperationResult<T> withResultImplementation(Class<? extends MultipleResult> resultImplementation) {
        this.resultImplementation = resultImplementation;
        return this;
    }

    public SingleResult<T> singleResult() {
        return multipleResult()
                .singleResult();
    }

    public MultipleResult<T> multipleResult() {
        return new WebMultipleResult<>(values);
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
