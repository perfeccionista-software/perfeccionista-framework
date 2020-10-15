package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextBlockMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;

public class WebTextBlockImpl extends AbstractWebChildElement implements WebTextBlock {

    // Actions

    @Override
    public WebTextBlock executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextBlock executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextBlock should(@NotNull WebTextBlockMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextBlock should(@NotNull GetTextAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Click

    @Override
    public WebTextBlock click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, String.class).execute(this, CLICK));
        return this;
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        return runCheck(getEnvironment(), InvocationName.of(GET_TEXT_METHOD, this),
                () -> getActionImplementation(GET_TEXT_METHOD, String.class).execute(this));
    }

    // HoverTo

    @Override
    public WebTextBlock hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebTextBlock scrollTo() {
        super.scrollTo();
        return this;
    }

}
