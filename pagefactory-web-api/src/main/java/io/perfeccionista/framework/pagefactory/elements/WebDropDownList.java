package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOpenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebDropDownList extends WebList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    // Actions
    @Override
    WebDropDownList executeAction(@NotNull String name, Object... args);
    @Override
    WebDropDownList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebDropDownList should(@NotNull WebDropDownListMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebListMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebDropDownList should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull GetTextAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull IsOpenAvailableMatcher matcher);

    // Click
    @Override
    WebDropDownList click();

    // Close
    @Override
    WebDropDownList close();

    // HoverTo
    @Override
    WebDropDownList hoverTo(boolean withOutOfBounds);

    // Open
    @Override
    WebDropDownList open();

    // ScrollTo
    @Override
    WebDropDownList scrollTo();

}