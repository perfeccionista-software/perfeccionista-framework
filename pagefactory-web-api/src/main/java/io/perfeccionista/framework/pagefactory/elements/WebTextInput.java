package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

public interface WebTextInput extends WebChildElement,
        WebGetTextAvailable, WebGetLabelAvailable, WebClickAvailable, WebInputTextAvailable, WebIsEnabledAvailable {

    // Actions
    @Override
    WebTextInput executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTextInput should(@NotNull WebTextInputMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebTextInput should(@NotNull WebIsEnabledAvailableMatcher matcher);

    // Click
    @Override
    WebTextInput click();

    // HoverTo
    @Override
    WebTextInput hoverTo(boolean withOutOfBounds);

    // InputText
    @Override
    WebTextInput clear();
    @Override
    WebTextInput typeText(@NotNull String text);
    @Override
    WebTextInput replaceText(@NotNull String text);
    @Override
    WebTextInput sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // ScrollTo
    @Override
    WebTextInput scrollTo();

}
