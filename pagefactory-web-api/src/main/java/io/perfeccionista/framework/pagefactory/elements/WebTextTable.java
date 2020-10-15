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
import io.perfeccionista.framework.matcher.element.WebTextTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

// TODO: Map<String, SingleResult<String>> extractHeader(String... columnNames, extractor);
// TODO: Map<String, MultipleResult<String>> extractAll(String... columnNames);
// TODO: Map<String, SingleResult<String>> extractFooter(String... columnNames);
public interface WebTextTable extends WebChildElement, ElementContainer<WebTextTableFilter, WebTextTableFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebTableFrame<DefaultWebTextBlock> getWebTextTableFrame();

    // Extractor
    @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractHeader(@NotNull WebTextTableCellValueExtractor<V> extractor);
    @NotNull <V> WebMultipleIndexedResult<V, WebTextTable> extractAllRows(@NotNull WebTextTableCellValueExtractor<V> extractor);
    @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractFooter(@NotNull WebTextTableCellValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebTextTableFilter filter(@NotNull WebTextTableFilterBuilder filterBuilder);

    // Actions
    @Override
    WebTextTable executeAction(@NotNull String name, Object... args);
    @Override
    WebTextTable executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTextTable should(@NotNull WebTextTableMatcher matcher);
    WebTextTable should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextTable should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTextTable hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextTable scrollTo();

}
