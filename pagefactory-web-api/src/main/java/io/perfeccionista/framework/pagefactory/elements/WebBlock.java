package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.factory.WebElementFrameFactory;
import org.jetbrains.annotations.NotNull;

public interface WebBlock<T extends WebBlock> extends WebChildElement, WebParentElement {

    static <T extends WebBlock> T frame(Class<T> blockClass) {
        return WebElementFrameFactory.createWebBlockFrame(blockClass);
    }

    @NotNull WebChildElement getElement(@NotNull String elementPath);
    @NotNull <T extends WebChildElement> T getElement(@NotNull String elementPath, @NotNull Class<T> elementClass);

    // Actions
    @Override
    T executeAction(@NotNull String name, Object... args);

    // Add
    @Override
    T addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector);
    @Override
    T addName(@NotNull String elementName);

    // Asserts
    @Override
    T should(@NotNull WebElementCondition... conditions);
    @Override
    T shouldNot(@NotNull WebElementCondition... conditions);

    // HoverTo
    @Override
    T hoverTo();
    @Override
    T hoverTo(@NotNull HoverOptions options);

    // PressKey
    @Override
    T press(@NotNull Key key);

    // ScrollTo
    @Override
    T scrollTo();
    @Override
    T scrollTo(@NotNull ScrollOptions options);

}
