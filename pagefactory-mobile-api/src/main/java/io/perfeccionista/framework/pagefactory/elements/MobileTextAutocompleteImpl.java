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

public class MobileTextAutocompleteImpl extends MobileTextDropDownListImpl implements MobileTextAutocomplete {

    // Actions

    @Override
    public MobileTextAutocomplete executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public MobileTextAutocomplete should(@NotNull MobileTextAutocompleteMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileTextDropDownListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileTextListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileGetLabelAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileGetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextAutocomplete should(@NotNull MobileIsOpenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // DropDown

    @Override
    public MobileTextAutocomplete open() {
        super.open();
        return this;
    }

    @Override
    public MobileTextAutocomplete close() {
        super.close();
        return this;
    }

    // InputText

    @Override
    public MobileTextAutocomplete clear() {
        MobileClearOperationType operationType = MobileClearOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, CLEAR).executeAction());
        return this;
    }

    @Override
    public MobileTextAutocomplete typeText(@NotNull String keys) {
        MobileTypeTextOperationType operationType = MobileTypeTextOperationType.of(this, keys);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public MobileTextAutocomplete replaceText(@NotNull String keys) {
        MobileReplaceTextOperationType operationType = MobileReplaceTextOperationType.of(this, keys);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public MobileTextAutocomplete sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        MobileSendKeyEventsOperationType operationType = MobileSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // ScrollTo

    @Override
    public MobileTextAutocomplete scrollTo() {
        super.scrollTo();
        return this;
    }

    // Tap


    @Override
    public MobileTextAutocomplete tap() {
        super.tap();
        return this;
    }

    @Override
    public MobileTextAutocomplete longTap() {
        super.longTap();
        return this;
    }

    @Override
    public MobileTextAutocomplete doubleTap() {
        super.doubleTap();
        return this;
    }

}
