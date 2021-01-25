package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextMatcher;
import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementAction;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;

public interface MobileToggleButton extends MobileButton, MobileIsSelectedAvailable {
    // Actions
    @Override
    MobileToggleButton executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileToggleButton should(@NotNull MobileTextMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileToggleButton should(@NotNull MobileGetTextAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileToggleButton scrollTo();

    // Tap
    @Override
    MobileToggleButton tap();

    @Override
    MobileToggleButton longTap();

    @Override
    MobileToggleButton doubleTap();
}
