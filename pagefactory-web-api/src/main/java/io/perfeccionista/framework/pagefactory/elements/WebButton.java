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
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebButtonMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebButton extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    // Actions
    @Override
    WebButton executeAction(@NotNull String name, Object... args);
    @Override
    WebButton executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebButton should(@NotNull WebButtonMatcher matcher);
    @Override
    WebButton should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebButton should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull GetTextAvailableMatcher matcher);

    // Click
    @Override
    WebButton click();

    // HoverTo
    @Override
    WebButton hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebButton scrollTo();

}