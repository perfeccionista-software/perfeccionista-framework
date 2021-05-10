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
import org.jetbrains.annotations.NotNull;

public interface WebLink extends WebText {

    // Actions
    @Override
    WebLink executeAction(@NotNull String name, Object... args);

    // Asserts
    WebLink should(@NotNull WebTextMatcher matcher);
    @Override
    WebLink should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebLink should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebLink should(@NotNull WebGetTextAvailableMatcher matcher);

    // Click
    @Override
    WebLink click();

    // HoverTo
    @Override
    WebLink hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebLink scrollTo();

}
