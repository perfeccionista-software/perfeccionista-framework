package io.perfeccionista.framework.pagefactory.elements;

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
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextMatcher;
import org.jetbrains.annotations.NotNull;

public interface MobileButton extends MobileText {

    // Actions
    @Override
    MobileButton executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileButton should(@NotNull MobileTextMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileButton should(@NotNull MobileGetTextAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileButton scrollTo();

    // Tap
    @Override
    MobileButton tap();

    @Override
    MobileButton longTap();

    @Override
    MobileButton doubleTap();

}
