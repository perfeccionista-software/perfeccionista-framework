package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_FILTERED_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;

public class WebListFilterSeleniumImpl implements WebListFilter {

    private final WebList element;
    private final WebListFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    private WebListFilterSeleniumImpl(WebList element, WebListFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebListFilterSeleniumImpl of(WebList element, WebListFilterBuilder filterBuilder) {
        return new WebListFilterSeleniumImpl(element, filterBuilder);
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    @Override
    public WebListFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
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
    public WebListFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        AtomicInteger i = new AtomicInteger(0);
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

    // TODO: Check this logic

    private void executeFilter(WebList element, WebListFilterBuilder filter) {
        Deque<WebListBlockConditionHolder> conditions = filter.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebListBlockConditionHolder conditionHolder : conditions) {
            WebListBlockCondition condition = conditionHolder.getCondition();
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

    private static WebFilterResult processConditions(WebList element, WebListBlockCondition condition, String hash) {
        WebFilterResult conditionResult = condition.process(element, hash);
        Deque<WebListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebListBlockConditionHolder childConditionHolder : childConditions) {
            WebListBlockCondition childCondition = childConditionHolder.getCondition();
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
