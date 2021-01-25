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
import io.perfeccionista.framework.matcher.element.MobileTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileDropDownAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileTextDropDownList extends MobileTextList,
        MobileTapAvailable, MobileGetTextAvailable, MobileGetLabelAvailable, MobileDropDownAvailable, MobileChildElement {

    // Actions
    @Override
    MobileTextDropDownList executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTextDropDownList should(@NotNull MobileTextDropDownListMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileTextListMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileGetLabelAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileGetTextAvailableMatcher matcher);
    @Override
    MobileTextDropDownList should(@NotNull MobileIsOpenAvailableMatcher matcher);

    // DropDown
    @Override
    MobileTextDropDownList open();
    @Override
    MobileTextDropDownList close();

    // ScrollTo
    @Override
    MobileTextDropDownList scrollTo();
//    @Override
//    MobileTextDropDownList scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileTextListFilterBuilder filterBuilder);
//    @Override
//    MobileTextDropDownList scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileTextListFilterBuilder filterBuilder);

    // Tap
    @Override
    MobileTextDropDownList tap();
    @Override
    MobileTextDropDownList longTap();
    @Override
    MobileTextDropDownList doubleTap();

}
