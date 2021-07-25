package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface WebDropDownList<T extends WebBlock> extends WebList<T>,
        WebClickAvailable, WebGetTextAvailable, WebGetLabelAvailable, WebDropDownAvailable, WebChildElement {

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
    @Override
    WebDropDownList<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebListMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebDropDownList<T> should(@NotNull WebDropDownAvailableMatcher matcher);

    // Click
    @Override
    WebDropDownList<T> click();

    // DropDown
    @Override
    WebDropDownList<T> open();
    @Override
    WebDropDownList<T> close();

    // HoverTo
    @Override
    WebDropDownList<T> hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebDropDownList<T> scrollTo();
//    @Override
//    WebDropDownList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);
//    @Override
//    WebDropDownList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);

}
