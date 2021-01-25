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
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileClearOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileReplaceTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileSendKeyEventsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileTypeTextOperationType;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;

public class MobileAutocompleteImpl extends MobileDropDownListImpl implements MobileAutocomplete {

    // Actions

    @Override
    public MobileAutocomplete executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileAutocomplete should(@NotNull MobileAutocompleteMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileDropDownListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileGetLabelAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileGetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileAutocomplete should(@NotNull MobileIsOpenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // DropDown

    @Override
    public MobileAutocomplete open() {
        super.open();
        return this;
    }

    @Override
    public MobileAutocomplete close() {
        super.close();
        return this;
    }

    // InputText

    @Override
    public MobileAutocomplete clear() {
        MobileClearOperationType operationType = MobileClearOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, CLEAR).executeAction());
        return this;
    }

    @Override
    public MobileAutocomplete typeText(@NotNull String keys) {
        MobileTypeTextOperationType operationType = MobileTypeTextOperationType.of(this, keys);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public MobileAutocomplete replaceText(@NotNull String keys) {
        MobileReplaceTextOperationType operationType = MobileReplaceTextOperationType.of(this, keys);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public MobileAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        MobileSendKeyEventsOperationType operationType = MobileSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // ScrollTo

    @Override
    public MobileAutocomplete scrollTo() {
        super.scrollTo();
        return this;
    }

//    @Override
//    public MobileAutocomplete scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder) {
//        super.scrollToHorizontally(scrollDirection, filterBuilder);
//        return this;
//    }
//
//    @Override
//    public MobileAutocomplete scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileListFilterBuilder filterBuilder) {
//        super.scrollToVertically(scrollDirection, filterBuilder);
//        return this;
//    }

    // Tap

    @Override
    public MobileAutocomplete tap() {
        super.tap();
        return this;
    }

    @Override
    public MobileAutocomplete longTap() {
        super.longTap();
        return this;
    }

    @Override
    public MobileAutocomplete doubleTap() {
        super.doubleTap();
        return this;
    }

}

