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
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import org.jetbrains.annotations.NotNull;

public interface WebTextAutocomplete extends WebTextDropDownList,
        SendKeysAvailable, ClearAvailable {

    // Actions
    @Override
    WebTextAutocomplete executeAction(@NotNull String name, Object... args);
    @Override
    WebTextAutocomplete executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTextAutocomplete should(@NotNull WebTextAutocompleteMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebTextDropDownListMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebTextListMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull GetTextAvailableMatcher matcher);
    @Override
    WebTextAutocomplete should(@NotNull IsOpenAvailableMatcher matcher);

    // Clear
    @Override
    WebTextAutocomplete clear();

    // Click
    @Override
    WebTextAutocomplete click();

    // Close
    @Override
    WebTextAutocomplete close();

    // HoverTo
    @Override
    WebTextAutocomplete hoverTo(boolean withOutOfBounds);

    // Open
    @Override
    WebTextAutocomplete open();

    // ScrollTo
    @Override
    WebTextAutocomplete scrollTo();

    // SendKeys
    @Override
    WebTextAutocomplete sendKeys(@NotNull String keys);
    @Override
    WebTextAutocomplete sendKeys(@NotNull KeysEventChain keyEvents);

}
