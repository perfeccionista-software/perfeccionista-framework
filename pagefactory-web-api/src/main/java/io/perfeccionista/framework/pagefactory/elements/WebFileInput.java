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
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebFileInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;

import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

/**
 * Input элемент для FileInput обычно скрыт,
 * поэтому тут удобнее объявлять корневым элементом родительский div
 */
public interface WebFileInput extends WebChildElement,
        WebClickAvailable, WebIsEnabledAvailable, WebGetLabelAvailable, WebGetTextAvailable, WebInputTextAvailable {

    WebFileInput uploadFromClasspath(@NotNull String... resourceName);
    WebFileInput uploadFromFile(@NotNull Path... file);

    // Actions
    @Override
    WebFileInput executeAction(@NotNull String name, Object... args);

    // Asserts
    WebFileInput should(@NotNull WebFileInputMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebIsEnabledAvailableMatcher matcher);

    // Click
    @Override
    WebFileInput click();

    // InputText
    @Override
    WebFileInput clear();
    @Override
    WebFileInput typeText(@NotNull String keys);
    @Override
    WebFileInput replaceText(@NotNull String keys);
    @Override
    WebFileInput sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // HoverTo
    @Override
    WebFileInput hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebFileInput scrollTo();

}
