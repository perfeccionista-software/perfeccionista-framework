package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static io.perfeccionista.framework.Web.emptyWebListFilter;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.FILTERED_ELEMENT_CONTAINS_NULL_RESULT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_INDEX_METHOD;
import static io.perfeccionista.framework.utils.StringUtils.indexesToString;

public class WebListSingleIndexedResult<T> implements WebSingleIndexedResult<T, WebList> {

    private final WebList element;
    private final WebListFilterBuilder filterBuilder;
    private final WebListBlockValueExtractor<T> extractor;

    private WebListSingleIndexedResult(@NotNull WebList element,
                                       @NotNull WebListFilterBuilder filterBuilder,
                                       @NotNull WebListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebListSingleIndexedResult<T> of(@NotNull WebList element,
                                                       @NotNull WebListFilterBuilder filterBuilder,
                                                       @NotNull WebListBlockValueExtractor<T> extractor) {
        return new WebListSingleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebListSingleIndexedResult<T> of(@NotNull WebList element,
                                                       @NotNull WebListBlockValueExtractor<T> extractor) {
        return new WebListSingleIndexedResult<>(element, emptyWebListFilter(), extractor);
    }

    @Override
    public @NotNull WebList getElement() {
        return element;
    }

    @Override
    public @Nullable T getResult() {
        WebListFilter webListFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_EXTRACTED_VALUE_METHOD, element, filterBuilder, extractor), () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webListFilter);
            if (extractedValues.size() > 1) {
                throw SingleResultCreating.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            return extractedValues.values().stream()
                    .findFirst()
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
       });
    }

    @Override
    public @NotNull T getNotNullResult() {
        WebListFilter webListFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_EXTRACTED_VALUE_METHOD, element, filterBuilder, extractor), () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webListFilter);
            if (extractedValues.size() > 1) {
                throw SingleResultCreating.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            Entry<Integer, T> extractedEntry = extractedValues.entrySet().stream()
                    .findFirst()
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
            T maybeNullValue = extractedEntry.getValue();
            if (Objects.isNull(maybeNullValue)) {
                throw ResultVerification.assertionError(FILTERED_ELEMENT_CONTAINS_NULL_RESULT.getMessage(extractedEntry.getKey()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
            }
            return maybeNullValue;
        });
    }

    @Override
    public int getIndex() {
        WebListFilter webListFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_INDEX_METHOD, element, filterBuilder, extractor), () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webListFilter);
            if (extractedValues.size() > 1) {
                throw SingleResultCreating.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            return extractedValues.keySet().stream()
                    .findFirst()
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
        });
    }

    @Override
    public WebSingleIndexedResult<T, WebList> should(WebMultipleIndexedResultMatcher<T> matcher) {
        WebListMultipleIndexedResult<T> result = WebListMultipleIndexedResult.of(element, filterBuilder, extractor);
        matcher.check(result);
        return this;
    }

}
