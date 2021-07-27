package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilter;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static io.perfeccionista.framework.Web.emptyWebTextBlockFilter;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.FILTERED_ELEMENT_CONTAINS_NULL_RESULT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_INDEX_METHOD;
import static io.perfeccionista.framework.utils.StringUtils.indexesToString;

public class WebTextListSingleIndexedResult<T> implements WebSingleIndexedResult<T, WebTextList> {

    private final WebTextList element;
    private final WebTextBlockFilterBuilder filterBuilder;
    private final WebTextListBlockValueExtractor<T> extractor;

    private WebTextListSingleIndexedResult(WebTextList element,
                                           WebTextBlockFilterBuilder filterBuilder,
                                           WebTextListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTextListSingleIndexedResult<T> of(@NotNull WebTextList element,
                                                           @NotNull WebTextBlockFilterBuilder filterBuilder,
                                                           @NotNull WebTextListBlockValueExtractor<T> extractor) {
        return new WebTextListSingleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTextListSingleIndexedResult<T> of(@NotNull WebTextList element,
                                                           @NotNull WebTextListBlockValueExtractor<T> extractor) {
        return new WebTextListSingleIndexedResult<>(element, emptyWebTextBlockFilter(), extractor);
    }

    @Override
    public @NotNull WebTextList getElement() {
        return element;
    }

    @Override
    public @Nullable T getResult() {
        WebTextBlockFilter webTextListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_EXTRACTED_VALUE_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webTextListFilter);
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
        WebTextBlockFilter webTextListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_EXTRACTED_VALUE_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webTextListFilter);
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
        WebTextBlockFilter webTextListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_INDEX_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webTextListFilter);
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
    public WebSingleIndexedResult<T, WebTextList> should(WebMultipleIndexedResultMatcher<T> matcher) {
        WebTextListMultipleIndexedResult<T> result = WebTextListMultipleIndexedResult.of(element, filterBuilder, extractor);
        matcher.check(result);
        return this;
    }

}
