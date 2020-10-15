package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
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
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetDimensionsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLocationAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebChildElement extends WebChildElementBase, WebComponentAvailable, WebElementPropertyAvailable,
        IsPresentAvailable, IsDisplayedAvailable, IsOnTheScreenAvailable, IsInFocusAvailable,
        HoverToAvailable, ScrollToAvailable,
        GetDimensionsAvailable, GetLocationAvailable, GetScreenshotAvailable, GetColorAvailable {

    @NotNull WebElementInteractionImplementation getInteractionImplementation(@NotNull String interactionName);

    WebChildElement executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    WebChildElement should(@NotNull WebChildElementMatcher matcher);

    @Override
    WebChildElement should(@NotNull GetColorAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull GetDimensionsAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull GetLocationAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull GetScreenshotAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull IsDisplayedAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull IsInFocusAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull IsOnTheScreenAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull IsPresentAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebComponentAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebElementPropertyAvailableMatcher matcher);

}