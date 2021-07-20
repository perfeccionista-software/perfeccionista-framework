package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition.WebTextListBlockConditionHolder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder.WebTextListBlockFilterResultGroupingHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import static io.perfeccionista.framework.Web.textBlock;
import static io.perfeccionista.framework.Web.textBlockIndex;
import static io.perfeccionista.framework.Web.textBlockValue;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SINGLE_RESULT_HAS_NO_VALUE;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public class WebTextListFilterImpl implements WebTextListFilter {

    private final WebTextList element;
    private final WebTextListFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private WebTextListFilterImpl(WebTextList element, WebTextListFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebTextListFilterImpl of(@NotNull WebTextList element, @NotNull WebTextListFilterBuilder filterBuilder) {
        return new WebTextListFilterImpl(element, filterBuilder);
    }

    @API(status = STABLE)
    public @NotNull WebSingleIndexedResult<String, WebTextList> extractOne() {
        return WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlockValue())
                .singleResult();
    }

    @API(status = STABLE)
    public @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll() {
        return WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlockValue());
    }

    @API(status = INTERNAL)
    public @NotNull <T> WebSingleIndexedResult<T, WebTextList> extractOne(@NotNull WebTextListBlockValueExtractor<T> extractor) {
        return WebTextListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @API(status = INTERNAL)
    public @NotNull <T> WebMultipleIndexedResult<T, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<T> extractor) {
        return WebTextListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public WebTextListFilter forSingleBlock(@NotNull Consumer<WebLink> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            DefaultWebTextBlock singleBlock = WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlock())
                    .singleResult()
                    .getNotNullResult();
            listBlockConsumer.accept(singleBlock.textLink());
        });
        return this;
    }

    @Override
    public WebTextListFilter forEachBlock(@NotNull Consumer<WebLink> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlock())
                    .getResults()
                    .forEach((key, value) -> listBlockConsumer.accept(value.textLink()));
        });
        return this;
    }

    @Override
    public WebTextListFilter forFirstBlock(@NotNull Consumer<WebLink> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            DefaultWebTextBlock firstBlock = WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlock())
                    .getResults().entrySet()
                    .stream()
                    .min(Entry.comparingByKey())
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)))
                    .getValue();
            listBlockConsumer.accept(firstBlock.textLink());
        });
        return this;
    }

    @Override
    public WebTextListFilter forLastBlock(@NotNull Consumer<WebLink> listBlockConsumer) {
        runCheck(InvocationInfo.assertInvocation(""), () -> {
            DefaultWebTextBlock lastBlock = WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlock())
                    .getResults().entrySet()
                    .stream()
                    .max(Entry.comparingByKey())
                    .orElseThrow(() -> SingleResultCreating.exception(SINGLE_RESULT_HAS_NO_VALUE.getMessage())
                            .setProcessed(true)
                            .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)))
                    .getValue();
            listBlockConsumer.accept(lastBlock.textLink());
        });
        return this;
    }

    @Override
    public @NotNull WebTextList getElement() {
        return element;
    }

    @Override
    public @NotNull FilterResult getFilterResult() {
        executeFilter(element, filterBuilder);
        return filterResult;
    }

    @Override
    public WebTextListFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebTextListMultipleIndexedResult<Integer> indexedResult = WebTextListMultipleIndexedResult.of(element, filterBuilder, textBlockIndex());
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
    public WebTextListFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(WebTextList element, WebTextListFilterBuilder filterBuilder) {
        Deque<WebTextListBlockFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebTextListBlockFilterResultGroupingHolder conditionHolder : conditions) {
            WebTextListBlockCondition condition = conditionHolder.getCondition();
            FilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (FilterResultGrouping.ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (FilterResultGrouping.SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = FilterResult.of(indexes, calculatedHash);
    }

    private FilterResult processConditions(WebTextList element, WebTextListBlockCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<WebTextListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebTextListBlockConditionHolder childConditionHolder : childConditions) {
            WebTextListBlockCondition childCondition = childConditionHolder.getCondition();
            FilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (ConditionGrouping.AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (ConditionGrouping.OR == childConditionHolder.getUsage()) {
                indexes.addAll(childConditionResult.getIndexes());
            }
        }
        return FilterResult.of(indexes, calculatedHash);
    }

}
