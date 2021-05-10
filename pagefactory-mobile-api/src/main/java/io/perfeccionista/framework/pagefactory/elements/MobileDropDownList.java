package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOpenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.MobileListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileDropDownAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import org.jetbrains.annotations.NotNull;

public interface MobileDropDownList extends MobileList,
        MobileTapAvailable, MobileGetTextAvailable, MobileGetLabelAvailable, MobileDropDownAvailable, MobileChildElement {

    // Actions
    @Override
    MobileDropDownList executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileDropDownList should(@NotNull MobileDropDownListMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    MobileDropDownList should(@NotNull MobileListMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileGetLabelAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileGetTextAvailableMatcher matcher);
    @Override
    MobileDropDownList should(@NotNull MobileIsOpenAvailableMatcher matcher);

    // DropDown
    @Override
    MobileDropDownList open();
    @Override
    MobileDropDownList close();

    // ScrollTo
    @Override
    MobileDropDownList scrollTo();
//    @Override
//    MobileDropDownList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder);
//    @Override
//    MobileDropDownList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder);

    // Tap
    @Override
    MobileDropDownList tap();
    @Override
    MobileDropDownList longTap();
    @Override
    MobileDropDownList doubleTap();

}
