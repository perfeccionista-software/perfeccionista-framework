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
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileClearOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileDoubleTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsEnabledOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetLabelOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileLongTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileReplaceTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileSendKeyEventsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileTypeTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileTapOperationType;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TAP;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class MobileTextInputImpl extends AbstractMobileChildElement implements MobileTextInput {

    // Actions

    @Override
    public MobileTextInput executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }
    // Asserts

    @Override
    public MobileTextInput should(@NotNull MobileTextInputMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileGetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileTextInput should(@NotNull MobileIsEnabledAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // InputText

    @Override
    public MobileTextInput clear() {
        MobileClearOperationType operationType = MobileClearOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, CLEAR).executeAction());
        return this;
    }

    @Override
    public MobileTextInput typeText(@NotNull String keys) {
        MobileTypeTextOperationType operationType = MobileTypeTextOperationType.of(this, keys);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public MobileTextInput replaceText(@NotNull String keys) {
        MobileReplaceTextOperationType operationType = MobileReplaceTextOperationType.of(this, keys);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public MobileTextInput sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        MobileSendKeyEventsOperationType operationType = MobileSendKeyEventsOperationType.of(this, keyEvents);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        MobileGetLabelOperationType operationType = MobileGetLabelOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, LABEL).executeGetter());
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        MobileGetTextOperationType operationType = MobileGetTextOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TEXT).executeGetter());
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        MobileGetIsEnabledOperationType operationType = MobileGetIsEnabledOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, ENABLED).executeGetter());
    }

    // ScrollTo

    @Override
    public MobileTextInput scrollTo() {
        super.scrollTo();
        return this;
    }

    // Tap

    @Override
    public MobileTextInput tap() {
        MobileTapOperationType operationType = MobileTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

    @Override
    public MobileTextInput longTap() {
        MobileLongTapOperationType operationType = MobileLongTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

    @Override
    public MobileTextInput doubleTap() {
        MobileDoubleTapOperationType operationType = MobileDoubleTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

}
