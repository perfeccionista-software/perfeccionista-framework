package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.conditions.WebListElementCondition;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface WebTable<H extends WebBlock<?>, T extends WebBlock<?>> extends WebList<T> {

    @API(status = Status.MAINTAINED)
    @NotNull H header();

    // Checks
    @Override
    WebTable<H, T> forEach(@NotNull Consumer<T> tableRowConsumer);
    @Override
    WebTable<H, T> forFirst(@NotNull Consumer<T> tableRowConsumer);
    @Override
    WebTable<H, T> forLast(@NotNull Consumer<T> tableRowConsumer);

    // Actions
    @Override
    WebTable<H, T> executeAction(@NotNull String name, Object... args);

    // Add
    @Override
    WebTable<H, T> addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector);
    @Override
    WebTable<H, T> addName(@NotNull String elementName);

    // Asserts
    @Override
    WebTable<H, T> should(@NotNull WebElementCondition... conditions);
    @Override
    WebTable<H, T> should(@NotNull WebListElementCondition... conditions);
    @Override
    WebTable<H, T> shouldNot(@NotNull WebElementCondition... conditions);
    @Override
    WebTable<H, T> shouldNot(@NotNull WebListElementCondition... conditions);

    // HoverTo
    @Override
    WebTable<H, T> hoverTo();
    @Override
    WebTable<H, T> hoverTo(@NotNull HoverOptions options);

    // PressKey
    @Override
    WebTable<H, T> press(@NotNull Key key);

    // ScrollTo
    @Override
    WebTable<H, T> scrollTo();
    @Override
    WebTable<H, T> scrollTo(@NotNull ScrollOptions options);

}
