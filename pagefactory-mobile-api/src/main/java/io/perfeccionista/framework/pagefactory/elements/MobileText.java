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
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileText extends MobileChildElement,
        MobileGetTextAvailable, MobileTapAvailable {

    // Actions
    @Override
    MobileText executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileText should(@NotNull MobileTextMatcher matcher);
    @Override
    MobileText should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileText should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileText should(@NotNull MobileGetTextAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileText scrollTo();

    // Tap
    @Override
    MobileText tap();
    @Override
    MobileText longTap();
    @Override
    MobileText doubleTap();

}
