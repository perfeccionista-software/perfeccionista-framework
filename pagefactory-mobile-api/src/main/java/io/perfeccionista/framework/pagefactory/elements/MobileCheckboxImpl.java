package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.element.MobileCheckboxMatcher;
import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileDoubleTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsEnabledOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsSelectedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetLabelOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileLongTapOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileTapOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TAP;

public class MobileCheckboxImpl extends AbstractMobileChildElement implements MobileCheckbox {

    // Actions

    @Override
    public MobileCheckbox executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileCheckbox should(@NotNull MobileCheckboxMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileIsEnabledAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public MobileCheckbox should(@NotNull MobileIsSelectedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        MobileGetLabelOperationType operationType = MobileGetLabelOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, LABEL).executeGetter());
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        MobileGetIsEnabledOperationType operationType = MobileGetIsEnabledOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, ENABLED).executeGetter());
    }

    // IsSelected

    @Override
    public boolean isSelected() {
        MobileGetIsSelectedOperationType operationType = MobileGetIsSelectedOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, SELECTED).executeGetter());
    }

    // ScrollTo

    @Override
    public MobileCheckbox scrollTo() {
        super.scrollTo();
        return this;
    }

    // Tap

    @Override
    public MobileCheckbox tap() {
        MobileTapOperationType operationType = MobileTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

    @Override
    public MobileCheckbox longTap() {
        MobileLongTapOperationType operationType = MobileLongTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

    @Override
    public MobileCheckbox doubleTap() {
        MobileDoubleTapOperationType operationType = MobileDoubleTapOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, TAP).executeAction());
        return this;
    }

}
