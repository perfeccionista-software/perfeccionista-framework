package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebBlockMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.factory.WebElementFrameFactory;
import org.jetbrains.annotations.NotNull;

public interface WebBlock extends WebChildElement, WebParentElement {

    static <T extends WebBlock> T frame(Class<T> blockClass) {
        return WebElementFrameFactory.createWebBlockFrame(blockClass);
    }

    // Actions
    @Override
    WebBlock executeAction(@NotNull String name, Object... args);
    @Override
    WebBlock executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebBlock should(@NotNull WebBlockMatcher matcher);
    @Override
    WebBlock should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebBlock should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebBlock should(@NotNull WebElementPropertyAvailableMatcher matcher);

    // HoverTo
    @Override
    WebBlock hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebBlock scrollTo();

}
