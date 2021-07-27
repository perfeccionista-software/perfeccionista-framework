package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition.WebListBlockConditionHolder;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder.WebListBlockFilterResultGroupingHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
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
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.*;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.*;

public class WebBlockFilterImpl<T extends WebBlock> implements WebBlockFilter<T> {

    private final WebList<T> element;
    private final WebBlockFilterBuilder<T> filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private WebBlockFilterImpl(WebList<T> element, WebBlockFilterBuilder<T> filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static <T extends WebBlock> WebBlockFilterImpl<T> of(@NotNull WebList<T> element, @NotNull WebBlockFilterBuilder<T> filterBuilder) {
        return new WebBlockFilterImpl<>(element, filterBuilder);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebBlockValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, WebBlockValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractorFunction.apply(element.getBlockFrame().getMappedBlockFrame()));
    }

    @Override
    public @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull WebBlockValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull Function<T, WebBlockValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractorFunction.apply(element.getBlockFrame().getMappedBlockFrame()))
                .singleResult();
    }

    @Override
    public WebBlockFilter<T> forSingle(@NotNull Consumer<T> blockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            T singleBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getBlockFrame().getMappedBlockClass()))
                    .singleResult()
                    .getNotNullResult();
            blockConsumer.accept(singleBlock);
        });
        return this;
    }

    @Override
    public WebBlockFilter<T> forEach(@NotNull Consumer<T> blockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getBlockFrame().getMappedBlockClass()))
                    .getResults()
                    .forEach((key, value) -> blockConsumer.accept(value));
        });
        return this;
    }

    @Override
    public WebBlockFilter<T> forFirst(@NotNull Consumer<T> blockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            T firstBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getBlockFrame().getMappedBlockClass()))
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
    public WebBlockFilter<T> forLast(@NotNull Consumer<T> blockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            T lastBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getBlockFrame().getMappedBlockClass()))
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
    public WebBlockFilter<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebListMultipleIndexedResult<Integer, T> indexedResult = WebListMultipleIndexedResult.of(element, filterBuilder, blockIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public int size() {
        return runCheck(InvocationInfo.getterInvocation(""), () -> {
            executeFilter(element, filterBuilder);
            return filterResult.getSize();
        });
    }

    @Override
    public WebBlockFilter<T> setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(WebList<T> element, WebBlockFilterBuilder<T> filterBuilder) {
        Deque<WebListBlockFilterResultGroupingHolder<T>> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebListBlockFilterResultGroupingHolder<T> conditionHolder : conditions) {
            WebBlockCondition<T> condition = conditionHolder.getCondition();
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

    private static <T extends WebBlock> FilterResult processConditions(WebList<T> element, WebBlockCondition<T> condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<WebListBlockConditionHolder<T>> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebListBlockConditionHolder<T> childConditionHolder : childConditions) {
            WebBlockCondition<T> childCondition = childConditionHolder.getCondition();
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
