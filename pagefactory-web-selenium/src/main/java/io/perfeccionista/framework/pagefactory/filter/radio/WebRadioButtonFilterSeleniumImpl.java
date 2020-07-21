package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
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

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_FILTERED_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;

public class WebRadioButtonFilterSeleniumImpl implements WebRadioButtonFilter {

    private final WebRadioGroup element;
    private final WebRadioButtonFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    public WebRadioButtonFilterSeleniumImpl(WebRadioGroup element, WebRadioButtonFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    @Override
    public WebRadioButtonFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    public <T> SingleResult<T> extractOne(WebRadioButtonValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filterBuilder.build(element))
                .singleResult();
    }

    public <T> MultipleResult<T> extractAll(WebRadioButtonValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filterBuilder.build(element));
    }

    public WebRadioButtonFilter shouldHaveSize(NumberValue<Integer> expectedSize) {
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

    private void executeFilter(WebRadioGroup element, WebRadioButtonFilterBuilder filterBuilder) {
        Deque<WebRadioButtonConditionHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebRadioButtonConditionHolder conditionHolder : conditions) {
            WebRadioButtonCondition condition = conditionHolder.getCondition();
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

    private static WebFilterResult processConditions(WebRadioGroup element, WebRadioButtonCondition condition, String hash) {
        WebFilterResult conditionResult = condition.process(element, hash);
        Deque<WebRadioButtonConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebRadioButtonConditionHolder childConditionHolder : childConditions) {
            WebRadioButtonCondition childCondition = childConditionHolder.getCondition();
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
