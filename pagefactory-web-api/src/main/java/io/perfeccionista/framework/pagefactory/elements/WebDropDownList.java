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
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import org.jetbrains.annotations.NotNull;

public interface WebDropDownList extends WebList,
        WebClickAvailable, WebGetTextAvailable, WebGetLabelAvailable, WebDropDownAvailable, WebChildElement {

    // Actions
    @Override
    WebDropDownList executeAction(@NotNull String name, Object... args);

    // Asserts
    WebDropDownList should(@NotNull WebDropDownListMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    WebDropDownList should(@NotNull WebListMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebIndexesMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebChildElementMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebGetColorAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebGetElementBoundsAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebGetScreenshotAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebIsDisplayedAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebIsInFocusAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebIsPresentAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebComponentAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebElementPropertyAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebElementStateAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebGetLabelAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebGetTextAvailableMatcher matcher);
    @Override
    WebDropDownList should(@NotNull WebDropDownAvailableMatcher matcher);

    // Click
    @Override
    WebDropDownList click();

    // DropDown
    @Override
    WebDropDownList open();
    @Override
    WebDropDownList close();

    // HoverTo
    @Override
    WebDropDownList hoverTo(boolean withOutOfBounds);

    // ScrollTo
    @Override
    WebDropDownList scrollTo();
//    @Override
//    WebDropDownList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);
//    @Override
//    WebDropDownList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebListFilterBuilder filterBuilder);

}
