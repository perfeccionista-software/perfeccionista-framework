package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_FILTERED_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public class WebTextListFilterSeleniumImpl implements WebTextListFilter {

    private final WebTextList element;
    private final WebTextListFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    public WebTextListFilterSeleniumImpl(WebTextList element, WebTextListFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    @Override
    public WebTextListFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    @API(status = STABLE)
    public SingleResult<String> extractOne() {
        return new WebTextListBlockStringExtractor()
                .extractValues(element, filterBuilder.build(element))
                .singleResult();
    }

    @API(status = STABLE)
    public MultipleResult<String> extractAll() {
        return new WebTextListBlockStringExtractor()
                .extractValues(element, filterBuilder.build(element));
    }

    @API(status = INTERNAL)
    public <T> SingleResult<T> extractOne(WebTextListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filterBuilder.build(element))
                .singleResult();
    }

    @API(status = INTERNAL)
    public <T> MultipleResult<T> extractAll(WebTextListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filterBuilder.build(element));
    }

    @Override
    public WebTextListFilter shouldHaveSize(NumberValue<Integer> expectedSize) {
        runCheck(element.getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize), () -> {
            executeFilter(element, filterBuilder);
            int actualSize = filterResult.getIndexes().size();
            if (!expectedSize.check(actualSize)) {
                throw new ElementSizeException(ELEMENT_FILTERED_SIZE_NOT_MATCH.getMessage())
                        .setType(ExceptionType.ASSERT)
                        .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addAttachmentEntry(StringAttachmentEntry.of("Size comparison result", expectedSize.toString()));
            }
        });
        return this;
    }

    private void executeFilter(WebTextList element, WebTextListFilterBuilder filterBuilder) {
        Deque<WebTextListBlockConditionHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebTextListBlockConditionHolder conditionHolder : conditions) {
            WebTextListBlockCondition condition = conditionHolder.getCondition();
            WebFilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ConditionUsage.ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (ConditionUsage.SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = WebFilterResult.of(indexes, calculatedHash);
    }

    private WebFilterResult processConditions(WebTextList element, WebTextListBlockCondition condition, String hash) {
        WebFilterResult conditionResult = condition.process(element, hash);
        Deque<WebTextListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebTextListBlockConditionHolder childConditionHolder : childConditions) {
            WebTextListBlockCondition childCondition = childConditionHolder.getCondition();
            WebFilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
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
        return WebFilterResult.of(indexes, calculatedHash);
    }

}
