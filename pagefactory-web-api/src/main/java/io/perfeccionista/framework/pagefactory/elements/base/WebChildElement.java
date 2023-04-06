package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementAttributeAvailable;
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
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import org.jetbrains.annotations.NotNull;

public interface WebChildElement extends WebChildElementBase,
        WebElementAttributeAvailable, WebElementStateAvailable,
        WebIsPresentAvailable, WebIsDisplayedAvailable, WebIsOnTheScreenAvailable, WebIsInFocusAvailable,
        WebHoverToAvailable, WebScrollToAvailable,
        WebGetElementBoundsAvailable, WebGetScreenshotAvailable, WebGetColorAvailable {

    boolean check(@NotNull WebElementCondition... conditions);
    boolean checkNot(@NotNull WebElementCondition... conditions);

    WebChildElement should(@NotNull WebElementCondition... conditions);
    WebChildElement shouldNot(@NotNull WebElementCondition... conditions);

    // Actions
    @Override
    WebChildElement executeAction(@NotNull String name, Object... args);

    // Add
    @Override
    WebChildElement addName(@NotNull String elementName);
    @Override
    WebChildElement addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector);

    // Hover
    @Override
    WebChildElement hoverTo();
    @Override
    WebChildElement hoverTo(@NotNull HoverOptions options);

    // Press key
    @Override
    WebChildElement press(@NotNull Key key);

    // Scroll
    @Override
    WebChildElement scrollTo();
    @Override
    WebChildElement scrollTo(@NotNull ScrollOptions options);

//    TODO: Точно нужны эти методы? Насколько они здесь логичны. Если мы ограничиваем функционал,
//     а потом даем возможность его расширитьSearch
//    @NotNull WebNode asNode();
//    @NotNull WebNode parentNode();
//    @NotNull WebNode childNode(@NotNull WebSelectorHolder rootSelector);
//    @NotNull WebList<WebNode> childNodes(@NotNull WebSelectorHolder rootSelector, @NotNull WebSelectorHolder itemSelector);

}
