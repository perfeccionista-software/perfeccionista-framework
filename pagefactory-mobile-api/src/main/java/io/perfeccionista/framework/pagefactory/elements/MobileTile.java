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

public interface MobileTile extends MobileButton {

    // Actions
    @Override
    MobileTile executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTile should(@NotNull MobileTextMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileTile should(@NotNull MobileGetTextAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileTile scrollTo();

    // Tap
    @Override
    MobileTile tap();
    @Override
    MobileTile longTap();
    @Override
    MobileTile doubleTap();

}
