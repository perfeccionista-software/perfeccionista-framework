package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsEnabledAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebRadioButtonMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_SELECTED_METHOD;

public class WebRadioButtonImpl extends AbstractWebChildElement implements WebRadioButton {

    // Actions

    @Override
    public WebRadioButton executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebRadioButton executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebRadioButton should(@NotNull WebRadioButtonMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull GetLabelAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull IsEnabledAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebRadioButton should(@NotNull IsSelectedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebRadioButton click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this, CLICK));
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        return runCheck(getEnvironment(), InvocationName.of(GET_LABEL_METHOD, this),
                () -> getActionImplementation(GET_LABEL_METHOD, String.class).execute(this));
    }

    // HoverTo

    @Override
    public WebRadioButton hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        return runCheck(getEnvironment(), InvocationName.of(IS_ENABLED_METHOD, this),
                () -> getActionImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this));
    }

    // IsSelected

    @Override
    public boolean isSelected() {
        return runCheck(getEnvironment(), InvocationName.of(IS_SELECTED_METHOD, this),
                () -> getActionImplementation(IS_SELECTED_METHOD, Boolean.class).execute(this));
    }

    // ScrollTo

    @Override
    public WebRadioButton scrollTo() {
        super.scrollTo();
        return this;
    }

}
