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
import io.perfeccionista.framework.matcher.element.MobileBlockMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import io.perfeccionista.framework.pagefactory.factory.MobileElementFrameFactory;
import org.jetbrains.annotations.NotNull;

public interface MobileBlock extends MobileChildElement, MobileParentElement {

    static <T extends MobileBlock> T frame(Class<T> blockClass) {
        return MobileElementFrameFactory.createMobileBlockFrame(blockClass);
    }

    // Search
    @NotNull MobileChildElement getElement(@NotNull String elementPath);
    <T extends MobileChildElement> @NotNull T getElement(@NotNull String elementPath, @NotNull Class<T> elementClass);

    // Actions
    @Override
    MobileBlock executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileBlock should(@NotNull MobileBlockMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileBlock should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileBlock scrollTo();
//    @Override
//    MobileBlock scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement);
//    @Override
//    MobileBlock scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement);

}
