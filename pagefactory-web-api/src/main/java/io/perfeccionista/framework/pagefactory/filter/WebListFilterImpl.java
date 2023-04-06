package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder.WebListFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition.WebListItemConditionHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.perfeccionista.framework.Web.block;
import static io.perfeccionista.framework.Web.blockIndex;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.*;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.*;

public class WebListFilterImpl<T extends WebBlock<?>> implements WebListFilter<T> {

    private final WebList<T> element;
    private final WebListFilterBuilder<T> filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private WebListFilterImpl(WebList<T> element, WebListFilterBuilder<T> filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static <T extends WebBlock<?>> WebListFilterImpl<T> of(@NotNull WebList<T> element, @NotNull WebListFilterBuilder<T> filterBuilder) {
        return new WebListFilterImpl<>(element, filterBuilder);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebItemValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, WebItemValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractorFunction.apply(element.getItemFrame().getMappedItemFrame()));
    }

    @Override
    public @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull WebItemValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull Function<T, WebItemValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractorFunction.apply(element.getItemFrame().getMappedItemFrame()))
                .singleResult();
    }

    @Override
    public WebListFilter<T> forSingle(@NotNull Consumer<T> blockConsumer) {
        MultipleAttemptInvocationWrapper.repeatInvocation(InvocationInfo.assertInvocation(""), () -> {
            T singleBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getItemFrame().getMappedItemClass()))
                    .singleResult()
                    .getNotNullResult();
            blockConsumer.accept(singleBlock);
        });
        return this;
    }

    @Override
    public WebListFilter<T> forEach(@NotNull Consumer<T> blockConsumer) {
        MultipleAttemptInvocationWrapper.repeatInvocation(InvocationInfo.assertInvocation(""), () -> {
            WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getItemFrame().getMappedItemClass()))
                    .getResults()
                    .forEach((key, value) -> blockConsumer.accept(value));
        });
        return this;
    }

    @Override
    public WebListFilter<T> forFirst(@NotNull Consumer<T> blockConsumer) {
        MultipleAttemptInvocationWrapper.repeatInvocation(InvocationInfo.assertInvocation(""), () -> {
            T firstBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getItemFrame().getMappedItemClass()))
                    .getResults().entrySet()
                    .stream()
                    .min(Entry.comparingByKey())
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)))
                    .getValue();
            blockConsumer.accept(firstBlock);
        });
        return this;
    }

    @Override
    public WebListFilter<T> forLast(@NotNull Consumer<T> blockConsumer) {
        MultipleAttemptInvocationWrapper.repeatInvocation(InvocationInfo.assertInvocation(""), () -> {
            T lastBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getItemFrame().getMappedItemClass()))
                    .getResults().entrySet()
                    .stream()
                    .max(Entry.comparingByKey())
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)))
                    .getValue();
            blockConsumer.accept(lastBlock);
        });
        return this;
    }

    @Override
    public @NotNull WebList<T> getElement() {
        return element;
    }

    @Override
    public @NotNull FilterResult getFilterResult() {
        executeFilter(element, filterBuilder);
        return filterResult;
    }

    @Override
    public WebListFilter<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebListMultipleIndexedResult<Integer, T> indexedResult = WebListMultipleIndexedResult.of(element, filterBuilder, blockIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public int size() {
        return MultipleAttemptInvocationWrapper.repeatInvocation(InvocationInfo.getterInvocation(""), () -> {
            executeFilter(element, filterBuilder);
            return filterResult.getSize();
        });
    }

    @Override
    public WebListFilter<T> setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(WebList<T> element, WebListFilterBuilder<T> filterBuilder) {
        Deque<WebListFilterResultGroupingHolder<T>> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebListFilterResultGroupingHolder<T> conditionHolder : conditions) {
            WebItemCondition<T> condition = conditionHolder.getCondition();
            FilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = FilterResult.of(indexes, calculatedHash);
    }

    private static <T extends WebBlock<?>> FilterResult processConditions(WebList<T> element, WebItemCondition<T> condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<WebListItemConditionHolder<T>> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebListItemConditionHolder<T> childConditionHolder : childConditions) {
            WebItemCondition<T> childCondition = childConditionHolder.getCondition();
            FilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (OR == childConditionHolder.getUsage()) {
                indexes.addAll(childConditionResult.getIndexes());
            }
        }
        return FilterResult.of(indexes, calculatedHash);
    }

}
