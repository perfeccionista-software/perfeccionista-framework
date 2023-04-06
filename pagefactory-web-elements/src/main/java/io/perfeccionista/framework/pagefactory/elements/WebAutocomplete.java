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
import io.perfeccionista.framework.matcher.methods.WebElementAttributeAvailableMatcher;
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

public interface WebAutocomplete<T extends WebBlock<?>> extends WebDropDownList<T>,
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
    WebAutocomplete<T> setText(@NotNull String keys);
    @Override
    WebAutocomplete<T> sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // ScrollTo
    @Override
    WebAutocomplete<T> scrollTo();

}
