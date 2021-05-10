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
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebCheckboxMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebCheckbox extends WebChildElement,
        WebClickAvailable, WebIsSelectedAvailable, WebIsEnabledAvailable, WebGetLabelAvailable {

    // Actions
    @Override
    WebCheckbox executeAction(@NotNull String name, Object... args);

    // Asserts
    WebCheckbox should(@NotNull WebCheckboxMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebIsEnabledAvailableMatcher matcher);
    @Override
    WebCheckbox should(@NotNull WebIsSelectedAvailableMatcher matcher);

    // Click
    @Override
    WebCheckbox click();

    // HoverTo
    @Override
    WebCheckbox hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebCheckbox scrollTo();

}
