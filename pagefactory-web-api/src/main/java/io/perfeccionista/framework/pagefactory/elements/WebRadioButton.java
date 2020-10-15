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
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebRadioButtonMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebRadioButton extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions
    @Override
    WebRadioButton executeAction(@NotNull String name, Object... args);
    @Override
    WebRadioButton executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebRadioButton should(@NotNull WebRadioButtonMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebRadioButton should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull IsEnabledAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull IsSelectedAvailableMatcher matcher);

    // Click
    @Override
    WebRadioButton click();

    // HoverTo
    @Override
    WebRadioButton hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebRadioButton scrollTo();

}
