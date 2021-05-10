package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebCheckboxMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsEnabledOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsSelectedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;

public class WebCheckboxImpl extends AbstractWebChildElement implements WebCheckbox {

    // Actions

    @Override
    public WebCheckbox executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebCheckbox should(@NotNull WebCheckboxMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebGetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebIsEnabledAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebCheckbox should(@NotNull WebIsSelectedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebCheckbox click() {
        WebClickOperationType operationType = WebClickOperationType.of(this);
        runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, CLICK).executeAction());
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, LABEL).executeGetter());
    }

    // HoverTo

    @Override
    public WebCheckbox hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ENABLED).executeGetter());
    }

    // IsSelected

    @Override
    public boolean isSelected() {
        WebGetIsSelectedOperationType operationType = WebGetIsSelectedOperationType.of(this);
        return runCheck(getEnvironment(), operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, SELECTED).executeGetter());
    }

    // ScrollTo

    @Override
    public WebCheckbox scrollTo() {
        super.scrollTo();
        return this;
    }

}
