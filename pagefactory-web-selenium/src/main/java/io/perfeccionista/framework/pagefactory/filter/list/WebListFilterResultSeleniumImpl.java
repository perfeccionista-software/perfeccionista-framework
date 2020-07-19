package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_FILTERED_SIZE_NOT_MATCH;

public class WebListFilterResultSeleniumImpl implements WebListFilterResult {

    private final WebList element;
    private final Set<Integer> indexes;
    private final String hash;

    private WebListFilterResultSeleniumImpl(WebList element, Set<Integer> indexes, String hash) {
        this.element = element;
        this.indexes = indexes;
        this.hash = hash;
    }

    public static WebListFilterResultSeleniumImpl of(WebList element, WebListFilter filter) {
        WebConditionProcessingResult result = executeFilter(element, filter);
        return new WebListFilterResultSeleniumImpl(element, result.getIndexes(), result.getHash());
    }

    @Override
    public @NotNull String getHash() {
        return hash;
    }

    @Override
    public Set<Integer> getIndexes() {
        return Set.copyOf(indexes);
    }

    @Override
    public @NotNull <T> SingleResult<T> extractOne(@NotNull WebListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this)
                .singleResult();
    }

    @Override
    public @NotNull <T> MultipleResult<T> extractAll(@NotNull WebListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this);
    }

    @Override
    public WebListFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        int actualSize = indexes.size();
        if (!expectedSize.check(actualSize)) {
            throw new ElementSizeException(ELEMENT_FILTERED_SIZE_NOT_MATCH.getMessage())
                    .setType(ExceptionType.ASSERT)
                    .setProcessed(true)
                    .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addAttachmentEntry(StringAttachmentEntry.of("Actual size", String.valueOf(actualSize)))
                    .addAttachmentEntry(StringAttachmentEntry.of("Expected size", expectedSize.toString()));
        }
        return this;
    }

    // TODO: Check this logic

    private static WebConditionProcessingResult executeFilter(WebList element, WebListFilter filter) {
        Deque<WebListBlockConditionHolder> conditions = filter.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = null;
        if (conditions.isEmpty()) {
            return new WebListBlockElementEmptyCondition().process(element, null);
        }
        for (WebListBlockConditionHolder conditionHolder : conditions) {
            WebListBlockCondition condition = conditionHolder.getCondition();
            WebConditionProcessingResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ConditionUsage.ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (ConditionUsage.SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        return WebConditionProcessingResult.of(indexes, calculatedHash);
    }

    private static WebConditionProcessingResult processConditions(WebList element, WebListBlockCondition condition, String hash) {
        WebConditionProcessingResult conditionResult = condition.process(element, hash);
        Deque<WebListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebListBlockConditionHolder childConditionHolder : childConditions) {
            WebListBlockCondition childCondition = childConditionHolder.getCondition();
            WebConditionProcessingResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (ConditionUsage.AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (ConditionUsage.OR == childConditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
        }
        return WebConditionProcessingResult.of(indexes, hash);
    }

}
