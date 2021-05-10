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
import io.perfeccionista.framework.matcher.element.MobileAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.MobileListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileInputTextAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

public interface MobileAutocomplete extends MobileDropDownList,
        MobileInputTextAvailable, MobileChildElement {

    // Actions
    @Override
    MobileAutocomplete executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileAutocomplete should(@NotNull MobileAutocompleteMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileDropDownListMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileListMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileGetLabelAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileGetTextAvailableMatcher matcher);
    @Override
    MobileAutocomplete should(@NotNull MobileIsOpenAvailableMatcher matcher);

    // DropDown
    @Override
    MobileAutocomplete open();
    @Override
    MobileAutocomplete close();

    // InputText
    @Override
    MobileAutocomplete clear();
    @Override
    MobileAutocomplete typeText(@NotNull String keys);
    @Override
    MobileAutocomplete replaceText(@NotNull String keys);
    @Override
    MobileAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // ScrollTo
    @Override
    MobileAutocomplete scrollTo();
//    @Override
//    MobileAutocomplete scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder);
//    @Override
//    MobileAutocomplete scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder);

    // Tap
    @Override
    MobileAutocomplete tap();
    @Override
    MobileAutocomplete longTap();
    @Override
    MobileAutocomplete doubleTap();

}
