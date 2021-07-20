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
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebList<T extends WebBlock> extends WebChildElement {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<T> getWebListFrame();

    // Extractor
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebListBlockValueExtractor<R, T> extractor);
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, ? extends WebListBlockValueExtractor<R, T>> extractorFunction);

    // Filter
    @NotNull WebListFilter<T> filterBuilder(@NotNull WebListFilterBuilder<T> filterBuilder);
    @NotNull WebListFilter<T> filterBuilder(@NotNull Function<T, ? extends WebListFilterBuilder<T>> filterBuilderFunction);
    @NotNull WebListFilter<T> filter(@NotNull WebListBlockCondition<T> filterCondition);
    @NotNull WebListFilter<T> filter(@NotNull Function<T, ? extends WebListBlockCondition<T>> filterConditionFunction);

    // Checks
    WebList<T> forEachBlock(@NotNull Consumer<T> listBlockConsumer);
    WebList<T> forFirstBlock(@NotNull Consumer<T> listBlockConsumer);
    WebList<T> forLastBlock(@NotNull Consumer<T> listBlockConsumer);

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

}
