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
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebTextList extends WebChildElement, ElementContainer<WebTextListFilter, WebTextListFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<DefaultWebTextBlock> getWebTextListFrame();

    // Extractor
    @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll();
    @NotNull <V> WebMultipleIndexedResult<V, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebTextListFilter filter(@NotNull WebTextListFilterBuilder filterBuilder);

    // Actions
    @Override
    WebTextList executeAction(@NotNull String name, Object... args);
    @Override
    WebTextList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTextList should(@NotNull WebTextListMatcher matcher);
    WebTextList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextList should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextList should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTextList hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextList scrollTo();

}