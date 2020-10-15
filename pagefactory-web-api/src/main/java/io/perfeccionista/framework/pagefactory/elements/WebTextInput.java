package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import org.jetbrains.annotations.NotNull;

public interface WebTextInput extends WebChildElement,
        GetTextAvailable, GetLabelAvailable, ClickAvailable, SendKeysAvailable, ClearAvailable, IsEnabledAvailable {

    // Actions
    @Override
    WebTextInput executeAction(@NotNull String name, Object... args);
    @Override
    WebTextInput executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTextInput should(@NotNull WebTextInputMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextInput should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull GetTextAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull IsEnabledAvailableMatcher matcher);

    // Clear
    @Override
    WebTextInput clear();

    // Click
    @Override
    WebTextInput click();

    // HoverTo
    @Override
    WebTextInput hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTextInput scrollTo();

    // SendKeys
    @Override
    WebTextInput sendKeys(@NotNull String keys);
    @Override
    WebTextInput sendKeys(@NotNull KeysEventChain keyEvents);

}