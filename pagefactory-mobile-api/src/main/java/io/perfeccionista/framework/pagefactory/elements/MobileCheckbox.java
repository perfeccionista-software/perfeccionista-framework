package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileCheckboxMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileCheckbox extends MobileChildElement,
        MobileTapAvailable, MobileIsSelectedAvailable, MobileIsEnabledAvailable, MobileGetLabelAvailable {

    // Actions
    @Override
    MobileCheckbox executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileCheckbox should(@NotNull MobileCheckboxMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileGetLabelAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileIsEnabledAvailableMatcher matcher);
    @Override
    MobileCheckbox should(@NotNull MobileIsSelectedAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileCheckbox scrollTo();

    // Tap
    @Override
    MobileCheckbox tap();
    @Override
    MobileCheckbox longTap();
    @Override
    MobileCheckbox doubleTap();

}
