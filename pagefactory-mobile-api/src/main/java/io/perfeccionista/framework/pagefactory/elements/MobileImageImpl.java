package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileImageMatcher;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileDoubleTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileLongTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileTapOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TAP;

public class MobileImageImpl extends AbstractMobileChildElement implements MobileImage {

    // Actions

    @Override
    public MobileImage executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileImage should(@NotNull MobileImageMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileChildElementMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileGetColorAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileComponentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileImage should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileImage scrollTo() {
        super.scrollTo();
        return this;
    }

    // Tap

    @Override
    public MobileImage tap() {
        MobileTapOperationType operationType = MobileTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }


    @Override
    public MobileImage longTap() {
        MobileLongTapOperationType operationType = MobileLongTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

    @Override
    public MobileImage doubleTap() {
        MobileDoubleTapOperationType operationType = MobileDoubleTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

}
