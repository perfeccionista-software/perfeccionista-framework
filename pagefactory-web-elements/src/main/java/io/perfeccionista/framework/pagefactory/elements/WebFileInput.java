package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.WebFileInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;

import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

/**
 * Input элемент для FileInput обычно скрыт,
 * поэтому тут удобнее объявлять корневым элементом родительский div
 */
public interface WebFileInput extends WebChildElement,
        WebClickAvailable, WebIsEnabledAvailable, WebGetTextAvailable, WebInputTextAvailable {

    WebFileInput uploadFromClasspath(@NotNull String... resourceName);
    WebFileInput uploadFromFile(@NotNull Path... file);

    // Actions
    @Override
    WebFileInput executeAction(@NotNull String name, Object... args);

    // Asserts
    WebFileInput should(@NotNull WebFileInputMatcher matcher);

    // Click
    @Override
    WebFileInput click();

    // InputText
    @Override
    WebFileInput clear();
    @Override
    WebFileInput setText(@NotNull String keys);
    @Override
    WebFileInput sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // ScrollTo
    @Override
    WebFileInput scrollTo();

}
