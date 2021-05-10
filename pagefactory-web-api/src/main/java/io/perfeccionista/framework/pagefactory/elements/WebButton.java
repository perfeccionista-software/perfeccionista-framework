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
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebTextMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebButton extends WebLink {

    // Actions
    @Override
    WebButton executeAction(@NotNull String name, Object... args);

    // Asserts
    WebButton should(@NotNull WebTextMatcher matcher);
    @Override
    WebButton should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebButton should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebButton should(@NotNull WebGetTextAvailableMatcher matcher);

    // Click
    @Override
    WebButton click();

    // HoverTo
    @Override
    WebButton hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebButton scrollTo();

}
