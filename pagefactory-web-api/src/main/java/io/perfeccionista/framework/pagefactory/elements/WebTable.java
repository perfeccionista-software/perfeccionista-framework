package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
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
public interface WebTable extends WebChildElement, ElementContainer<WebTableFilter, WebTableFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebTableFrame<WebBlock> getWebTableFrame();

    // Extractor
    @NotNull <V> WebSingleIndexedResult<V, WebTable> extractHeader(@NotNull WebTableCellValueExtractor<V> extractor);
    @NotNull <V> WebMultipleIndexedResult<V, WebTable> extractAllRows(@NotNull WebTableCellValueExtractor<V> extractor);
    @NotNull <V> WebSingleIndexedResult<V, WebTable> extractFooter(@NotNull WebTableCellValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebTableFilter filter(@NotNull WebTableFilterBuilder filterBuilder);

    // Actions
    @Override
    WebTable executeAction(@NotNull String name, Object... args);
    @Override
    WebTable executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTable should(@NotNull WebTableMatcher matcher);
    WebTable should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTable should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTable should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTable should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTable hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTable scrollTo();

}