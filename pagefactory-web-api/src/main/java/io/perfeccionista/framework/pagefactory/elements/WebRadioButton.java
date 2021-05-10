package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebRadioButtonMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebRadioButton extends WebChildElement,
        WebClickAvailable, WebIsSelectedAvailable, WebIsEnabledAvailable, WebGetLabelAvailable {

    // Actions
    @Override
    WebRadioButton executeAction(@NotNull String name, Object... args);

    // Asserts
    WebRadioButton should(@NotNull WebRadioButtonMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebIsEnabledAvailableMatcher matcher);
    @Override
    WebRadioButton should(@NotNull WebIsSelectedAvailableMatcher matcher);

    // Click
    @Override
    WebRadioButton click();

    // HoverTo
    @Override
    WebRadioButton hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebRadioButton scrollTo();

}
