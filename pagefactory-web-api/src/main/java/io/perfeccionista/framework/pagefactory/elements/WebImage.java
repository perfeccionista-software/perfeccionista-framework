package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebImageMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import org.jetbrains.annotations.NotNull;

// TODO: Сделать встроенную проверку того, что это изображение (по тегу src)
// TODO: Добавить метод saveToFile(Path file); который сохранит изображение в указанный файл.
public interface WebImage extends WebChildElement,
        ClickAvailable {

    WebImage saveToFile(@NotNull String filePath);

    // Actions
    @Override
    WebImage executeAction(@NotNull String name, Object... args);

    @Override
    WebImage executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebImage should(@NotNull WebImageMatcher matcher);
    @Override
    WebImage should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebImage should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebImage should(@NotNull WebElementPropertyAvailableMatcher matcher);

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
