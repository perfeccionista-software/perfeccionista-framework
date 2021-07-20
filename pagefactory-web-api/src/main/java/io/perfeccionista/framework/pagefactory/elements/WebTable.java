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
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

// TODO: Map<String, SingleResult<T>> extractHeader(Map<String, WebTableCellValueExtractor<V>> columnExtractors);
// TODO: Map<String, MultipleResult<T>> extractAll(Map<String, WebTableCellValueExtractor<V>> columnExtractors);
// TODO: Map<String, SingleResult<T>> extractFooter(Map<String, WebTableCellValueExtractor<V>> columnExtractors);
public interface WebTable extends WebChildElement, WebElementContainer<WebTableFilter, WebTableFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebTableFrame<WebBlock> getWebTableFrame();

    // Extractor
    @NotNull <V> WebSingleIndexedResult<V, WebTable> extractHeader(@NotNull WebTableValueExtractor<V> extractor);
    @NotNull <V> WebMultipleIndexedResult<V, WebTable> extractRows(@NotNull WebTableValueExtractor<V> extractor);
    @NotNull <V> WebSingleIndexedResult<V, WebTable> extractFooter(@NotNull WebTableValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebTableFilter filterBuilder(@NotNull WebTableFilterBuilder filterBuilder);
    @NotNull WebTableFilter filter(@NotNull WebTableRowCondition filterCondition);

    // Actions
    @Override
    WebTable executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTable should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebTable should(@NotNull WebTableMatcher matcher);
    WebTable should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTable should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTable should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTable hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTable scrollTo();
//    WebTable scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebTableFilterBuilder filterBuilder);
//    WebTable scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebTableFilterBuilder filterBuilder);

}
