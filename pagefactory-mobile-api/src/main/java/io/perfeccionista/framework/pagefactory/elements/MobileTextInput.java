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
import io.perfeccionista.framework.matcher.method.MobileIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextInputMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileInputTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

public interface MobileTextInput extends MobileChildElement,
        MobileGetTextAvailable, MobileGetLabelAvailable, MobileTapAvailable, MobileInputTextAvailable, MobileIsEnabledAvailable {

    // Actions
    @Override
    MobileTextInput executeAction(@NotNull String name, Object... args);

    // Asserts
    MobileTextInput should(@NotNull MobileTextInputMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileChildElementMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileGetColorAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileGetScreenshotAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileIsDisplayedAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileIsInFocusAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileIsPresentAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileComponentAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileElementPropertyAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileElementStateAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileGetLabelAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileGetTextAvailableMatcher matcher);
    @Override
    MobileTextInput should(@NotNull MobileIsEnabledAvailableMatcher matcher);

    // InputText
    @Override
    MobileTextInput clear();
    @Override
    MobileTextInput typeText(@NotNull String keys);
    @Override
    MobileTextInput replaceText(@NotNull String keys);
    @Override
    MobileTextInput sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    // ScrollTo
    @Override
    MobileTextInput scrollTo();

    // Tap
    @Override
    MobileTextInput tap();
    @Override
    MobileTextInput longTap();
    @Override
    MobileTextInput doubleTap();

}
