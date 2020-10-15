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
import io.perfeccionista.framework.matcher.element.WebLinkMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebLink extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    // Actions
    @Override
    WebLink executeAction(@NotNull String name, Object... args);
    @Override
    WebLink executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebLink should(@NotNull WebLinkMatcher matcher);
    @Override
    WebLink should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebLink should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull GetTextAvailableMatcher matcher);

    // Click
    @Override
    WebLink click();

    // HoverTo
    @Override
    WebLink hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebLink scrollTo();

}