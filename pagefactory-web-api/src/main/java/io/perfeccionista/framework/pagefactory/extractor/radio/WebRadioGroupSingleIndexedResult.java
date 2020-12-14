package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.exceptions.WebResultVerification;
import io.perfeccionista.framework.exceptions.WebSingleResult;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SINGLE_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.FILTERED_ELEMENT_CONTAINS_NULL_RESULT;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_EXTRACTED_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_INDEX_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebRadioButtonFilter;
import static io.perfeccionista.framework.utils.StringUtils.indexesToString;

public class WebRadioGroupSingleIndexedResult<T> implements WebSingleIndexedResult<T, WebRadioGroup> {

    private final WebRadioGroup element;
    private final WebRadioGroupFilterBuilder filterBuilder;
    private final WebRadioButtonValueExtractor<T> extractor;

    private WebRadioGroupSingleIndexedResult(WebRadioGroup element,
                                             WebRadioGroupFilterBuilder filterBuilder,
                                             WebRadioButtonValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebRadioGroupSingleIndexedResult<T> of(@NotNull WebRadioGroup element,
                                                             @NotNull WebRadioGroupFilterBuilder filterBuilder,
                                                             @NotNull WebRadioButtonValueExtractor<T> extractor) {
        return new WebRadioGroupSingleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebRadioGroupSingleIndexedResult<T> of(@NotNull WebRadioGroup element,
                                                             @NotNull WebRadioButtonValueExtractor<T> extractor) {
        return new WebRadioGroupSingleIndexedResult<>(element, emptyWebRadioButtonFilter(), extractor);
    }

    @Override
    public @NotNull WebRadioGroup getElement() {
        return element;
    }

    @Override
    public @Nullable T getValue() {
        WebRadioGroupFilter webRadioGroupFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), getterInvocation(GET_EXTRACTED_VALUE_METHOD, element, filterBuilder, extractor), () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webRadioGroupFilter);
            if (extractedValues.size() > 1) {
                throw WebSingleResult.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            return extractedValues.values().stream()
                    .findFirst()
                    .orElseThrow(() -> WebSingleResult.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
        });
    }

    @Override
    public @NotNull T getNotNullValue() {
        WebRadioGroupFilter webRadioGroupFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), getterInvocation(GET_EXTRACTED_VALUE_METHOD, element, filterBuilder, extractor), () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webRadioGroupFilter);
            if (extractedValues.size() > 1) {
                throw WebSingleResult.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            Entry<Integer, T> extractedEntry = extractedValues.entrySet().stream()
                    .findFirst()
                    .orElseThrow(() -> WebSingleResult.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
            T maybeNullValue = extractedEntry.getValue();
            if (Objects.isNull(maybeNullValue)) {
                throw WebResultVerification.assertionError(FILTERED_ELEMENT_CONTAINS_NULL_RESULT.getMessage(extractedEntry.getKey()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
            }
            return maybeNullValue;
        });
    }

    @Override
    public int getIndex() {
        WebRadioGroupFilter webRadioGroupFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), getterInvocation(GET_INDEX_METHOD, element, filterBuilder, extractor), () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webRadioGroupFilter);
            if (extractedValues.size() > 1) {
                throw WebSingleResult.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            return extractedValues.keySet().stream()
                    .findFirst()
                    .orElseThrow(() -> WebSingleResult.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
        });
    }

    @Override
    public WebSingleIndexedResult<T, WebRadioGroup> should(WebMultipleIndexedResultMatcher<T> matcher) {
        WebRadioGroupMultipleIndexedResult<T> result = WebRadioGroupMultipleIndexedResult.of(element, filterBuilder, extractor);
        matcher.check(result);
        return this;
    }

}
