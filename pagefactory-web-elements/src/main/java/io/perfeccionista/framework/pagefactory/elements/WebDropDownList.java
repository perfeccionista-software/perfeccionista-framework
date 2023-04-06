package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface WebDropDownList<T extends WebBlock<?>> extends WebList<T>,
        WebClickAvailable, WebGetTextAvailable, WebDropDownAvailable, WebChildElement {

    // Checks
    @Override
    WebDropDownList<T> forEach(@NotNull Consumer<T> blockConsumer);
    @Override
    WebDropDownList<T> forFirst(@NotNull Consumer<T> blockConsumer);
    @Override
    WebDropDownList<T> forLast(@NotNull Consumer<T> blockConsumer);

    // Actions
    @Override
    WebDropDownList<T> executeAction(@NotNull String name, Object... args);

    // Asserts
    WebDropDownList<T> should(@NotNull WebDropDownListMatcher matcher);

    // Click
    @Override
    WebDropDownList<T> click();

    // DropDown
    @Override
    WebDropDownList<T> open();
    @Override
    WebDropDownList<T> close();

//    @Override
//    WebDropDownList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);
//    @Override
//    WebDropDownList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);

}
