package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.ResultVerification;
import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.MobileExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.MobileFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.MobileFilters;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.FILTERED_ELEMENT_CONTAINS_NULL_RESULT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_INDEX_METHOD;
import static io.perfeccionista.framework.utils.StringUtils.indexesToString;

public class MobileListSingleIndexedResult<T> implements MobileSingleIndexedResult<T, MobileList> {

    private final MobileList element;
    private final MobileListFilterBuilder filterBuilder;
    private final MobileListBlockValueExtractor<T> extractor;

    private MobileListSingleIndexedResult(@NotNull MobileList element,
                                       @NotNull MobileListFilterBuilder filterBuilder,
                                       @NotNull MobileListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> MobileListSingleIndexedResult<T> of(@NotNull MobileList element,
                                                       @NotNull MobileListFilterBuilder filterBuilder,
                                                       @NotNull MobileListBlockValueExtractor<T> extractor) {
        return new MobileListSingleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> MobileListSingleIndexedResult<T> of(@NotNull MobileList element,
                                                       @NotNull MobileListBlockValueExtractor<T> extractor) {
        return new MobileListSingleIndexedResult<>(element, MobileFilters.emptyMobileListFilter(), extractor);
    }

    @Override
    public @NotNull MobileList getElement() {
        return element;
    }

    @Override
    public @Nullable T getResult() {
        MobileListFilter webListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_EXTRACTED_VALUE_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(MobileExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webListFilter);
            if (extractedValues.size() > 1) {
                throw SingleResultCreating.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            return extractedValues.values().stream()
                    .findFirst()
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
        });
    }

    @Override
    public @NotNull T getNotNullResult() {
        MobileListFilter webListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_EXTRACTED_VALUE_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(MobileExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webListFilter);
            if (extractedValues.size() > 1) {
                throw SingleResultCreating.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            Entry<Integer, T> extractedEntry = extractedValues.entrySet().stream()
                    .findFirst()
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
            T maybeNullValue = extractedEntry.getValue();
            if (Objects.isNull(maybeNullValue)) {
                throw ResultVerification.assertionError(FILTERED_ELEMENT_CONTAINS_NULL_RESULT.getMessage(extractedEntry.getKey()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
            }
            return maybeNullValue;
        });
    }

    @Override
    public int getIndex() {
        MobileListFilter webListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_INDEX_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(MobileExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> {
            Map<Integer, T> extractedValues = extractor.extractValues(webListFilter);
            if (extractedValues.size() > 1) {
                throw SingleResultCreating.exception(SINGLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                        .setProcessed(true)
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("Values", indexesToString(extractedValues.keySet())));
            }
            return extractedValues.keySet().stream()
                    .findFirst()
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
        });
    }

    @Override
    public MobileSingleIndexedResult<T, MobileList> should(MobileMultipleIndexedResultMatcher<T> matcher) {
        MobileListMultipleIndexedResult<T> result = MobileListMultipleIndexedResult.of(element, filterBuilder, extractor);
        matcher.check(result);
        return this;
    }

}
