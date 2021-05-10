package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebTile extends WebButton {

    // Actions
    @Override
    WebTile executeAction(@NotNull String name, Object... args);

    // Asserts
    WebTile should(@NotNull WebTextMatcher matcher);
    @Override
    WebTile should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTile should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebTile should(@NotNull WebGetTextAvailableMatcher matcher);

    // Click
    @Override
    WebTile click();

    // HoverTo
    @Override
    WebTile hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebTile scrollTo();

}
