package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebCheckboxMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebCheckbox extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions
    @Override
    WebCheckbox executeAction(@NotNull String name, Object... args);
    @Override
    WebCheckbox executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebCheckbox should(@NotNull WebCheckboxMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebCheckbox should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull IsEnabledAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull IsSelectedAvailableMatcher matcher);

    // Click
    @Override
    WebCheckbox click();

    // HoverTo
    @Override
    WebCheckbox hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebCheckbox scrollTo();

}