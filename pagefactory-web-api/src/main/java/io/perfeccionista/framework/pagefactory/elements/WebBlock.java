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
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.factory.WebElementFrameFactory;
import org.jetbrains.annotations.NotNull;

public interface WebBlock extends WebChildElement, WebParentElement {

    static <T extends WebBlock> T frame(Class<T> blockClass) {
        return WebElementFrameFactory.createWebBlockFrame(blockClass);
    }

    // Search
    @NotNull WebChildElement getElement(@NotNull String elementPath);
    <T extends WebChildElement> @NotNull T getElement(@NotNull String elementPath, @NotNull Class<T> elementClass);

    // Actions
    @Override
    WebBlock executeAction(@NotNull String name, Object... args);

    // Asserts
    WebBlock should(@NotNull WebBlockMatcher matcher);
    @Override
    WebBlock should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebBlock should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebElementStateAvailableMatcher matcher);

    // HoverTo
    @Override
    WebBlock hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebBlock scrollTo();
//    @Override
//    WebBlock scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebChildElement childElement);
//    @Override
//    WebBlock scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebChildElement childElement);

}
