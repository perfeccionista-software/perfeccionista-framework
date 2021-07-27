package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebBlockFrame;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebList<T extends WebBlock> extends WebChildElement {

    @API(status = Status.MAINTAINED)
    @NotNull WebBlockFrame<T> getBlockFrame();

    // Extractor
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebBlockValueExtractor<R, T> extractor);
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, ? extends WebBlockValueExtractor<R, T>> extractorFunction);

    // Filter
    @NotNull WebBlockFilter<T> filterBuilder(@NotNull WebBlockFilterBuilder<T> filterBuilder);
    @NotNull WebBlockFilter<T> filterBuilder(@NotNull Function<T, ? extends WebBlockFilterBuilder<T>> filterBuilderFunction);
    @NotNull WebBlockFilter<T> filter(@NotNull WebBlockCondition<T> filterCondition);
    @NotNull WebBlockFilter<T> filter(@NotNull Function<T, ? extends WebBlockCondition<T>> filterConditionFunction);

    // Checks
    WebList<T> forEach(@NotNull Consumer<T> blockConsumer);
    WebList<T> forFirst(@NotNull Consumer<T> blockConsumer);
    WebList<T> forLast(@NotNull Consumer<T> blockConsumer);

    // Actions
    @Override
    WebList<T> executeAction(@NotNull String name, Object... args);

    // Asserts
    WebList<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebList<T> should(@NotNull WebListMatcher matcher);
    WebList<T> should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebList<T> should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebList<T> hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebList<T> scrollTo();
//    WebList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);
//    WebList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);

    // Size
    int size();

}
