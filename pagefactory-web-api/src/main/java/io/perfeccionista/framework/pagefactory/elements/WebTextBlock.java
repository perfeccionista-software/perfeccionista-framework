package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextBlockMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebTextBlock extends WebChildElement,
        GetTextAvailable, ClickAvailable {

    // Actions
    @Override
    WebTextBlock executeAction(@NotNull String name, Object... args);
    @Override
    WebTextBlock executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTextBlock should(@NotNull WebTextBlockMatcher matcher);
    @Override
    WebTextBlock should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextBlock should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextBlock should(@NotNull GetTextAvailableMatcher matcher);

    // Click
    @Override
    WebTextBlock click();

    // HoverTo
    @Override
    WebTextBlock hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextBlock scrollTo();

}
