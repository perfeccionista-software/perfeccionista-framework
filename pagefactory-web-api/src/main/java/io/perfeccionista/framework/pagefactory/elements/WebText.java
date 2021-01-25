package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebText extends WebChildElement,
        WebGetTextAvailable, WebClickAvailable {

    // Actions
    @Override
    WebText executeAction(@NotNull String name, Object... args);

    // Asserts
    WebText should(@NotNull WebTextMatcher matcher);
    @Override
    WebText should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebText should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebText should(@NotNull WebGetTextAvailableMatcher matcher);

    // Click
    @Override
    WebText click();

    // HoverTo
    @Override
    WebText hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebText scrollTo();

}
