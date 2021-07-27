package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.WebListMatcher;
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
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebTable<H extends WebBlock, T extends WebBlock> extends WebList<T> {

    @API(status = Status.MAINTAINED)
    @NotNull H header();

    // Filter
    @Override
    @NotNull WebBlockFilter<T> filterBuilder(@NotNull WebBlockFilterBuilder<T> filterBuilder);
    @Override
    @NotNull WebBlockFilter<T> filterBuilder(@NotNull Function<T, ? extends WebBlockFilterBuilder<T>> filterBuilderFunction);
    @Override
    @NotNull WebBlockFilter<T> filter(@NotNull WebBlockCondition<T> filterCondition);
    @Override
    @NotNull WebBlockFilter<T> filter(@NotNull Function<T, ? extends WebBlockCondition<T>> filterConditionFunction);

    // Checks
    @Override
    WebTable<H, T> forEach(@NotNull Consumer<T> tableRowConsumer);
    @Override
    WebTable<H, T> forFirst(@NotNull Consumer<T> tableRowConsumer);
    @Override
    WebTable<H, T> forLast(@NotNull Consumer<T> tableRowConsumer);

    // Actions
    @Override
    WebTable<H, T> executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTable<H, T> should(@NotNull WebTableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebTable<H, T> should(@NotNull WebListMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTable<H, T> should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTable<H, T> hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTable<H, T> scrollTo();
//    WebTable scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebTableFilterBuilder filterBuilder);
//    WebTable scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebTableFilterBuilder filterBuilder);

}
