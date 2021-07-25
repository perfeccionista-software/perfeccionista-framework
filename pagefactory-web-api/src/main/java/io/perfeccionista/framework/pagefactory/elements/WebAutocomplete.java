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

import java.util.function.Consumer;

public interface WebAutocomplete<T extends WebBlock> extends WebDropDownList<T>,
        WebInputTextAvailable, WebChildElement {

    // Checks
    @Override
    WebAutocomplete<T> forEach(@NotNull Consumer<T> blockConsumer);
    @Override
    WebAutocomplete<T> forFirst(@NotNull Consumer<T> blockConsumer);
    @Override
    WebAutocomplete<T> forLast(@NotNull Consumer<T> blockConsumer);

    // Actions
    @Override
    WebAutocomplete<T> executeAction(@NotNull String name, Object... args);

    // Asserts
    WebAutocomplete<T> should(@NotNull WebAutocompleteMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebDropDownListMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebListMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebAutocomplete<T> should(@NotNull WebDropDownAvailableMatcher matcher);

    // Click
    @Override
    WebAutocomplete<T> click();

    // DropDown
    @Override
    WebAutocomplete<T> open();
    @Override
    WebAutocomplete<T> close();

    // InputText
    @Override
    WebAutocomplete<T> clear();
    @Override
    WebAutocomplete<T> typeText(@NotNull String keys);
    @Override
    WebAutocomplete<T> replaceText(@NotNull String keys);
    @Override
    WebAutocomplete<T> sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // HoverTo
    @Override
    WebAutocomplete<T> hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebAutocomplete<T> scrollTo();

}
