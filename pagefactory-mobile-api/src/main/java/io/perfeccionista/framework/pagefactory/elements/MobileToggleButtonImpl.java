package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.MobileChildElementMatcher;
import io.perfeccionista.framework.matcher.element.MobileTextMatcher;
import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.method.MobileIsSelectedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsSelectedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;

public class MobileToggleButtonImpl extends MobileButtonImpl implements MobileToggleButton {

    // Actions

    @Override
    public MobileToggleButton executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public MobileToggleButton should(@NotNull MobileTextMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public MobileToggleButton should(@NotNull MobileGetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // ScrollTo

    @Override
    public MobileToggleButton scrollTo() {
        super.scrollTo();
        return this;
    }

    // Tap

    @Override
    public MobileToggleButton tap() {
        super.tap();
        return this;
    }

    @Override
    public MobileToggleButton longTap() {
        super.longTap();
        return this;
    }

    @Override
    public MobileToggleButton doubleTap() {
        super.doubleTap();
        return this;
    }

    @Override
    public boolean isSelected() {
        MobileGetIsSelectedOperationType operationType = MobileGetIsSelectedOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType, SELECTED).executeGetter());
    }

    @Override
    public MobileIsSelectedAvailable should(@NotNull MobileIsSelectedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

}
