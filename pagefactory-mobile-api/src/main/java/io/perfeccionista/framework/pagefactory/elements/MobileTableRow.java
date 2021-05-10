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
import org.jetbrains.annotations.NotNull;

public interface MobileTableRow extends MobileBlock {

    // Search
    @NotNull MobileBlock getCell(@NotNull String columnName);
    <T extends MobileBlock> @NotNull T getCell(@NotNull String columnName, @NotNull Class<T> cellClass);

    // Actions
    @Override
    MobileTableRow executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTableRow should(@NotNull MobileBlockMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTableRow should(@NotNull MobileElementStateAvailableMatcher matcher);

    // ScrollTo
    @Override
    MobileTableRow scrollTo();
//    @Override
//    MobileTableRow scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement);
//    @Override
//    MobileTableRow scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement);

}
