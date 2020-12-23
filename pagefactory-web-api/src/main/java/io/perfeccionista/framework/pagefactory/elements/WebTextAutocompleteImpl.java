package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOpenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebTextAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebTextDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebTextListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_METHOD;

public class WebTextAutocompleteImpl extends WebTextDropDownListImpl implements WebTextAutocomplete {

    // Actions

    @Override
    public WebTextAutocomplete executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextAutocomplete executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextAutocomplete should(@NotNull WebTextAutocompleteMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebTextDropDownListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebTextListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull GetLabelAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull GetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebTextAutocomplete should(@NotNull IsOpenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // Clear

    @Override
    public WebTextAutocomplete clear() {
        runCheck(getEnvironment(), actionInvocation(CLEAR_METHOD, this),
                () -> getActionImplementation(CLEAR_METHOD, Boolean.class).execute(this));
        return this;
    }

    // Click

    @Override
    public WebTextAutocomplete click() {
        super.click();
        return this;
    }

    // Close

    @Override
    public WebTextAutocomplete close() {
        super.close();
        return this;
    }

    // HoverTo

    @Override
    public WebTextAutocomplete hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // Open

    @Override
    public WebTextAutocomplete open() {
        super.open();
        return this;
    }

    // ScrollTo

    @Override
    public WebTextAutocomplete scrollTo() {
        super.scrollTo();
        return this;
    }

    // SendKeys

    @Override
    public WebTextAutocomplete sendKeys(@NotNull String keys) {
        runCheck(getEnvironment(), actionInvocation(SEND_KEYS_METHOD, this, keys),
                () -> getActionImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys));
        return this;
    }

    @Override
    public WebTextAutocomplete sendKeys(@NotNull KeysEventChain keyEvents) {
        runCheck(getEnvironment(), actionInvocation(SEND_KEYS_EVENTS_METHOD, this, keyEvents),
                () -> getActionImplementation(SEND_KEYS_EVENTS_METHOD, Void.class).execute(this, keyEvents));
        return this;
    }

}
