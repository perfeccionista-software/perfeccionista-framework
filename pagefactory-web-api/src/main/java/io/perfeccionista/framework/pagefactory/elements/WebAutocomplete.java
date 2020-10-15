package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOpenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import org.jetbrains.annotations.NotNull;

public interface WebAutocomplete extends WebDropDownList,
        SendKeysAvailable, ClearAvailable {

    // Actions
    @Override
    WebAutocomplete executeAction(@NotNull String name, Object... args);
    @Override
    WebAutocomplete executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebAutocomplete should(@NotNull WebAutocompleteMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebDropDownListMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebListMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull GetTextAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull IsOpenAvailableMatcher matcher);

    // Clear
    @Override
    WebAutocomplete clear();

    // Click
    @Override
    WebAutocomplete click();

    // Close
    @Override
    WebAutocomplete close();

    // HoverTo
    @Override
    WebAutocomplete hoverTo(boolean withOutOfBounds);

    // Open
    @Override
    WebAutocomplete open();

    // ScrollTo
    @Override
    WebAutocomplete scrollTo();

    // SendKeys
    @Override
    WebAutocomplete sendKeys(@NotNull String keys);
    @Override
    WebAutocomplete sendKeys(@NotNull KeysEventChain keyEvents);

}