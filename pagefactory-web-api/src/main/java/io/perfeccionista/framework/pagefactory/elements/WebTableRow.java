package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebBlockMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebTableRow extends WebBlock {

    // Search
    @NotNull WebBlock getCell(@NotNull String columnName);
    <T extends WebBlock> @NotNull T getCell(@NotNull String columnName, @NotNull Class<T> cellClass);

    // Actions
    @Override
    WebTableRow executeAction(@NotNull String name, Object... args);
    @Override
    WebTableRow executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTableRow should(@NotNull WebBlockMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTableRow should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTableRow hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTableRow scrollTo();

}
