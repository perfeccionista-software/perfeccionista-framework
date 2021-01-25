package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementStateAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetElementBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileScrollToAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileChildElement extends MobileChildElementBase, MobileComponentAvailable,
        MobileElementPropertyAvailable, MobileElementStateAvailable,
        MobileIsPresentAvailable, MobileIsDisplayedAvailable, MobileIsOnTheScreenAvailable, MobileIsInFocusAvailable,
        MobileScrollToAvailable,
        MobileGetElementBoundsAvailable, MobileGetScreenshotAvailable, MobileGetColorAvailable {

    MobileChildElement should(@NotNull MobileChildElementMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileGetColorAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileIsInFocusAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileGetScreenshotAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileIsDisplayedAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileIsPresentAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileComponentAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileElementPropertyAvailableMatcher matcher);

    @Override
    MobileChildElement should(@NotNull MobileElementStateAvailableMatcher matcher);

}
