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
import io.perfeccionista.framework.matcher.element.MobileTextAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextListMatcher;
import io.perfeccionista.framework.matcher.result.MobileIndexesMatcher;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileInputTextAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

public interface MobileTextAutocomplete extends MobileTextDropDownList,
        MobileInputTextAvailable, MobileChildElement {

    // Actions
    @Override
    MobileTextAutocomplete executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTextAutocomplete should(@NotNull MobileTextAutocompleteMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileTextDropDownListMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileTextListMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileIndexesMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileGetLabelAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileGetTextAvailableMatcher matcher);
    @Override
    MobileTextAutocomplete should(@NotNull MobileIsOpenAvailableMatcher matcher);

    // DropDown
    @Override
    MobileTextAutocomplete open();
    @Override
    MobileTextAutocomplete close();

    // InputText
    @Override
    MobileTextAutocomplete clear();
    @Override
    MobileTextAutocomplete typeText(@NotNull String keys);
    @Override
    MobileTextAutocomplete replaceText(@NotNull String keys);
    @Override
    MobileTextAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // ScrollTo
    @Override
    MobileTextAutocomplete scrollTo();
//    @Override
//    MobileTextAutocomplete scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileTextListFilterBuilder filterBuilder);
//    @Override
//    MobileTextAutocomplete scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileTextListFilterBuilder filterBuilder);

    // Tap
    @Override
    MobileTextAutocomplete tap();
    @Override
    MobileTextAutocomplete longTap();
    @Override
    MobileTextAutocomplete doubleTap();

}

