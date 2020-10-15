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
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebTextDropDownList extends WebTextList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    // Actions
    @Override
    WebTextDropDownList executeAction(@NotNull String name, Object... args);
    @Override
    WebTextDropDownList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts
    WebTextDropDownList should(@NotNull WebTextDropDownListMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebTextListMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull GetColorAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull GetDimensionsAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull GetLocationAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull GetScreenshotAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull IsDisplayedAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull IsInFocusAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull IsOnTheScreenAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull IsPresentAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull GetLabelAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull GetTextAvailableMatcher matcher);
    @Override
    WebTextDropDownList should(@NotNull IsOpenAvailableMatcher matcher);

    // Click
    @Override
    WebTextDropDownList click();

    // Close
    @Override
    WebTextDropDownList close();

    // HoverTo
    @Override
    WebTextDropDownList hoverTo(boolean withOutOfBounds);

    // Open
    @Override
    WebTextDropDownList open();

    // ScrollTo
    @Override
    WebTextDropDownList scrollTo();

}
