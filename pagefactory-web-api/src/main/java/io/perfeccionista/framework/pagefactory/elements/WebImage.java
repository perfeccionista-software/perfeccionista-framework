package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebImageMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebImage extends WebChildElement,
        WebClickAvailable {

    boolean isImage();

    WebImage saveToFile(@NotNull String filePath);

    // Actions
    @Override
    WebImage executeAction(@NotNull String name, Object... args);

    // Asserts
    WebImage should(@NotNull WebImageMatcher matcher);
    @Override
    WebImage should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebImage should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebElementStateAvailableMatcher matcher);

    // Click
    @Override
    WebImage click();

    // HoverTo
    @Override
    WebImage hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebImage scrollTo();

}
