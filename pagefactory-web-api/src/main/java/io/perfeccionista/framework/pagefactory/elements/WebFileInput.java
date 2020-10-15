package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebFileInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;

import org.jetbrains.annotations.NotNull;

/**
 * Input элемент для FileInput обычно скрыт,
 * поэтому тут удобнее объявлять корневым элементом родительский div
 */
public interface WebFileInput extends WebChildElement,
        ClearAvailable, ClickAvailable, IsEnabledAvailable, GetLabelAvailable, GetTextAvailable {

    WebFileInput setFileName(@NotNull String filePath);

    // Actions
    @Override
    WebFileInput executeAction(@NotNull String name, Object... args);
    @Override
    WebFileInput executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebFileInput should(@NotNull WebFileInputMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebFileInput should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull GetTextAvailableMatcher matcher);
    @Override
    WebFileInput should(@NotNull IsEnabledAvailableMatcher matcher);

    // Clear
    @Override
    WebFileInput clear();

    @Override
    WebFileInput click();

    // HoverTo
    @Override
    WebFileInput hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebFileInput scrollTo();

}
