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
import io.perfeccionista.framework.matcher.element.WebRadioGroupMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementContainer;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebRadioGroup extends WebChildElement, ElementContainer<WebRadioGroupFilter, WebRadioGroupFilterBuilder> {

    @API(status = Status.MAINTAINED)
    @NotNull WebRadioGroupFrame<DefaultWebRadioButtonBlock> getWebRadioGroupFrame();

    // Extractor
    @NotNull <V> WebMultipleIndexedResult<V, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<V> extractor);

    // Filter
    @Override
    @NotNull WebRadioGroupFilter filter(@NotNull WebRadioGroupFilterBuilder filterBuilder);

    // Button Extractors
    @NotNull WebRadioButton getSelected();
    @NotNull WebRadioButton getByIndex(int expectedIndex);
    @NotNull WebRadioButton getByLabel(@NotNull String expectedText);

    // Actions
    @Override
    WebRadioGroup executeAction(@NotNull String name, Object... args);
    @Override
    WebRadioGroup executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebRadioGroup should(@NotNull WebRadioGroupMatcher matcher);
    WebRadioGroup should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebRadioGroup should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebRadioGroup hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebRadioGroup scrollTo();

}
