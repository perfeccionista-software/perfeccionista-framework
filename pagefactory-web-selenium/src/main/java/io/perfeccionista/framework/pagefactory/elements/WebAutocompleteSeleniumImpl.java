package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;

// WebAutocomplete
@WebElementAction(name = CLEAR_METHOD, implementation = SeleniumClear.class)
@WebElementAction(name = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public class WebAutocompleteSeleniumImpl extends WebDropDownListSeleniumImpl implements WebAutocomplete {

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
    public WebAutocomplete should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebAutocomplete should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
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

    // ClickToElement

    @Override
    public WebAutocomplete clickToElement(@NotNull WebListFilterBuilder filter) {
        super.clickToElement(filter);
        return this;
    }

    // Close

    @Override
    public WebAutocomplete close() {
        super.close();
        return this;
    }

    // Get Color

    @Override
    public WebAutocomplete componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebAutocomplete componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Label

    @Override
    public WebAutocomplete shouldHaveLabel(@NotNull StringValue expectedValue) {
        super.shouldHaveLabel(expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldHaveLabel(@NotNull NumberValue<?> expectedValue) {
        super.shouldHaveLabel(expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldNotHaveLabel(@NotNull StringValue expectedValue) {
        super.shouldNotHaveLabel(expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue) {
        super.shouldNotHaveLabel(expectedValue);
        return this;
    }

    // Get Location

    @Override
    public WebAutocomplete componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebAutocomplete componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // Get Text

    @Override
    public WebAutocomplete shouldHaveText(@NotNull StringValue expectedValue) {
        super.shouldHaveText(expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldHaveText(@NotNull NumberValue<?> expectedValue) {
        super.shouldHaveText(expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldNotHaveText(@NotNull StringValue expectedValue) {
        super.shouldNotHaveText(expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldNotHaveText(@NotNull NumberValue<?> expectedValue) {
        super.shouldNotHaveText(expectedValue);
        return this;
    }

    // HoverTo

    @Override
    public WebAutocomplete hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebAutocomplete shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebAutocomplete shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebAutocomplete shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebAutocomplete shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsOpen

    @Override
    public WebAutocomplete shouldBeOpen() {
        super.shouldBeOpen();
        return this;
    }

    @Override
    public WebAutocomplete shouldBeClosed() {
        super.shouldBeClosed();
        return this;
    }

    // IsPresent

    @Override
    public WebAutocomplete shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebAutocomplete shouldNotBePresent() {
        super.shouldNotBePresent();
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

    // ScrollToElement

    @Override
    public WebAutocomplete scrollToElement(@NotNull WebListFilterBuilder filter) {
        super.scrollToElement(filter);
        return this;
    }

    // Size

    @Override
    public WebAutocomplete shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        super.shouldHaveSize(expectedSize);
        return this;
    }

    // SendKeys

    @Override
    public WebAutocomplete sendKeys(CharSequence... keys) {
        runCheck(getEnvironment(), InvocationName.of(SEND_KEYS_METHOD, this, keys),
                () -> getActionImplementation(SEND_KEYS_METHOD, Void.class).execute(this, List.of(keys)));
        return this;
    }

    // WebComponents

    @Override
    public WebAutocomplete componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebAutocomplete componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebAutocomplete shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebAutocomplete shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
