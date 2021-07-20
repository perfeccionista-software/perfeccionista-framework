package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition.WebListBlockConditionHolder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder.WebListBlockFilterResultGroupingHolder;
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

public class WebListFilterImpl<T extends WebBlock> implements WebListFilter<T> {

    private final WebList<T> element;
    private final WebListFilterBuilder<T> filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private WebListFilterImpl(WebList<T> element, WebListFilterBuilder<T> filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static <T extends WebBlock> WebListFilterImpl<T> of(@NotNull WebList<T> element, @NotNull WebListFilterBuilder<T> filterBuilder) {
        return new WebListFilterImpl<>(element, filterBuilder);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebListBlockValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, WebListBlockValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractorFunction.apply(element.getWebListFrame().getMappedBlockFrame()));
    }

    @Override
    public @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull WebListBlockValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull Function<T, WebListBlockValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractorFunction.apply(element.getWebListFrame().getMappedBlockFrame()))
                .singleResult();
    }

    @Override
    public WebListFilter<T> forSingleBlock(@NotNull Consumer<T> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            T singleBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getWebListFrame().getMappedBlockClass()))
                    .singleResult()
                    .getNotNullResult();
            listBlockConsumer.accept(singleBlock);
        });
        return this;
    }

    @Override
    public WebListFilter<T> forEachBlock(@NotNull Consumer<T> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getWebListFrame().getMappedBlockClass()))
                    .getResults()
                    .forEach((key, value) -> listBlockConsumer.accept(value));
        });
        return this;
    }

    @Override
    public WebListFilter<T> forFirstBlock(@NotNull Consumer<T> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            T firstBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getWebListFrame().getMappedBlockClass()))
                    .getResults().entrySet()
                    .stream()
                    .min(Entry.comparingByKey())
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)))
                    .getValue();
            listBlockConsumer.accept(firstBlock);
        });
        return this;
    }

    @Override
    public WebListFilter<T> forLastBlock(@NotNull Consumer<T> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            T lastBlock = WebListMultipleIndexedResult.of(element, filterBuilder, block(element.getWebListFrame().getMappedBlockClass()))
                    .getResults().entrySet()
                    .stream()
                    .max(Entry.comparingByKey())
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)))
                    .getValue();
            listBlockConsumer.accept(lastBlock);
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
        return runCheck(InvocationInfo.getterInvocation(""), () -> {
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
        Deque<WebListBlockFilterResultGroupingHolder<T>> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebListBlockFilterResultGroupingHolder<T> conditionHolder : conditions) {
            WebListBlockCondition<T> condition = conditionHolder.getCondition();
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

    private static <T extends WebBlock> FilterResult processConditions(WebList<T> element, WebListBlockCondition<T> condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<WebListBlockConditionHolder<T>> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebListBlockConditionHolder<T> childConditionHolder : childConditions) {
            WebListBlockCondition<T> childCondition = childConditionHolder.getCondition();
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
