package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.invocation.runner.InvocationName;
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
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebAutocompleteMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.element.WebDropDownListMatcher;
import io.perfeccionista.framework.matcher.element.WebListMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_METHOD;

public class WebAutocompleteImpl extends WebDropDownListImpl implements WebAutocomplete {

    // Actions

    @Override
    public WebAutocomplete executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebAutocomplete executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebAutocomplete should(@NotNull WebAutocompleteMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull WebDropDownListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull WebListMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull WebIndexesMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull IsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull GetLabelAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull GetTextAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebAutocomplete should(@NotNull IsOpenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // Clear

    @Override
    public WebAutocomplete clear() {
        runCheck(getEnvironment(), InvocationName.of(CLEAR_METHOD, this),
                () -> getActionImplementation(CLEAR_METHOD, Void.class).execute(this));
        return this;
    }

    // Click

    @Override
    public WebAutocomplete click() {
        super.click();
        return this;
    }

    // Close

    @Override
    public WebAutocomplete close() {
        super.close();
        return this;
    }

    // HoverTo

    @Override
    public WebAutocomplete hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // Open

    @Override
    public WebAutocomplete open() {
        super.open();
        return this;
    }

    // ScrollTo

    @Override
    public WebAutocomplete scrollTo() {
        super.scrollTo();
        return this;
    }

    // SendKeys

    @Override
    public WebAutocomplete sendKeys(@NotNull String keys) {
        runCheck(getEnvironment(), InvocationName.of(SEND_KEYS_METHOD, this, keys),
                () -> getActionImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys));
        return this;
    }

    @Override
    public WebAutocomplete sendKeys(@NotNull KeysEventChain keyEvents) {
        runCheck(getEnvironment(), InvocationName.of(SEND_KEYS_EVENTS_METHOD, this, keyEvents),
                () -> getActionImplementation(SEND_KEYS_EVENTS_METHOD, Void.class).execute(this, keyEvents));
        return this;
    }

}
