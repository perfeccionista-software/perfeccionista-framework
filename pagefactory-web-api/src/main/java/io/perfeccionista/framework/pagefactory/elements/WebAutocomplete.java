package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

public interface WebAutocomplete extends WebDropDownList,
        WebInputTextAvailable, WebChildElement {

    // Actions
    @Override
    WebAutocomplete executeAction(@NotNull String name, Object... args);

    // Asserts
    WebAutocomplete should(@NotNull WebAutocompleteMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebDropDownListMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebAutocomplete should(@NotNull WebListMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebAutocomplete should(@NotNull WebDropDownAvailableMatcher matcher);

    // Click
    @Override
    WebAutocomplete click();

    // DropDown
    @Override
    WebAutocomplete open();
    @Override
    WebAutocomplete close();

    // InputText
    @Override
    WebAutocomplete clear();
    @Override
    WebAutocomplete typeText(@NotNull String keys);
    @Override
    WebAutocomplete replaceText(@NotNull String keys);
    @Override
    WebAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // HoverTo
    @Override
    WebAutocomplete hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebAutocomplete scrollTo();

}
