package io.perfeccionista.framework.pagefactory.elements;

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
import io.perfeccionista.framework.matcher.element.MobileImageMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileImage extends MobileChildElement,
        MobileTapAvailable {

    // TODO: Нужно понять можно ли сохранять эти изображения
//    MobileImage saveToFile(@NotNull String filePath);

    // Actions
    @Override
    MobileImage executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileImage should(@NotNull MobileImageMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileImage should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileImage scrollTo();

    // Tap
    @Override
    MobileImage tap();
    @Override
    MobileImage longTap();
    @Override
    MobileImage doubleTap();

}
