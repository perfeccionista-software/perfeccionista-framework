package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
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
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementStateAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebChildElement extends WebChildElementBase, WebComponentAvailable,
        WebElementPropertyAvailable, WebElementStateAvailable,
        WebIsPresentAvailable, WebIsDisplayedAvailable, WebIsOnTheScreenAvailable, WebIsInFocusAvailable,
        WebHoverToAvailable, WebScrollToAvailable,
        WebGetElementBoundsAvailable, WebGetScreenshotAvailable, WebGetColorAvailable {

    WebChildElement should(@NotNull WebChildElementMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebGetColorAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebGetElementBoundsAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebGetScreenshotAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebIsDisplayedAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebIsInFocusAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebIsPresentAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebComponentAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebElementPropertyAvailableMatcher matcher);

    @Override
    WebChildElement should(@NotNull WebElementStateAvailableMatcher matcher);

}
