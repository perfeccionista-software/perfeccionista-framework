package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter.WebListBlockConditionHolder;
import io.perfeccionista.framework.value.number.NumberValue;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

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
    public String getHash() {
        return hash;
    }

    @Override
    public Set<Integer> getIndexes() {
        return Set.copyOf(indexes);
    }

    @Override
    public <T> SingleResult<T> extractOne(WebListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this)
                .singleResult();
    }

    @Override
    public <T> MultipleResult<T> extractAll(WebListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this);
    }

    @Override
    public WebListFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        new WebListBlockIndexExtractor()
                .extractValues(element, this)
                .shouldHaveSize(integerValue);
        return this;
    }

    private static WebConditionProcessingResult executeFilter(WebList element, WebListFilter filter) {
        Deque<WebListBlockConditionHolder> conditions = filter.getConditions();
        if (conditions.isEmpty()) {
            return WebConditionProcessingResult.of(Set.of(-1), null);
        }
        String calculatedHash = null;
        Set<Integer> indexes = new HashSet<>();
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
