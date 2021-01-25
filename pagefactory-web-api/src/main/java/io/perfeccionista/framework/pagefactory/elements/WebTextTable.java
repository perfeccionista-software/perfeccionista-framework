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
import io.perfeccionista.framework.matcher.element.WebTextTableMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
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
public interface WebTextTable extends WebChildElement, WebElementContainer<WebTextTableFilter, WebTextTableFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebTableFrame<DefaultWebTextBlock> getWebTextTableFrame();

    // Extractor
    @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractHeader(@NotNull WebTextTableValueExtractor<V> extractor);
    @NotNull <V> WebMultipleIndexedResult<V, WebTextTable> extractRows(@NotNull WebTextTableValueExtractor<V> extractor);
    @NotNull <V> WebSingleIndexedResult<V, WebTextTable> extractFooter(@NotNull WebTextTableValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebTextTableFilter filter(@NotNull WebTextTableFilterBuilder filterBuilder);
    @NotNull WebTextTableFilter filter(@NotNull WebTextTableRowCondition filterCondition);

    // Actions
    @Override
    WebTextTable executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTextTable should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebTextTable should(@NotNull WebTextTableMatcher matcher);
    WebTextTable should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextTable should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTextTable hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextTable scrollTo();
//    WebTextTable scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebTextTableFilterBuilder filterBuilder);
//    WebTextTable scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebTextTableFilterBuilder filterBuilder);

}
