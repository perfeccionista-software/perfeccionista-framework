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
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebList extends WebChildElement, ElementContainer<WebListFilter, WebListFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<WebBlock> getWebListFrame();

    // Extractor
    @NotNull <V> WebMultipleIndexedResult<V, WebList> extractAll(@NotNull WebListBlockValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebListFilter filter(@NotNull WebListFilterBuilder filterBuilder);

    // Actions
    @Override
    WebList executeAction(@NotNull String name, Object... args);
    @Override
    WebList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebList should(@NotNull WebListMatcher matcher);
    WebList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebList should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebList should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebList should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebList should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebList should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebList should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebList should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebList should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebList should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebList hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebList scrollTo();

}
