package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebBlockMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebTableRow extends WebBlock {

    // Search
    @NotNull WebBlock getCell(@NotNull String columnName);
    <T extends WebBlock> @NotNull T getCell(@NotNull String columnName, @NotNull Class<T> cellClass);

    // Actions
    @Override
    WebTableRow executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTableRow should(@NotNull WebBlockMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTableRow should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebTableRow hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTableRow scrollTo();
//    @Override
//    WebTableRow scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebChildElement childElement);
//    @Override
//    WebTableRow scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebChildElement childElement);

}
