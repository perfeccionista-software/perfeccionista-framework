package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.util.List;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;

public class WebTextAutocompleteSeleniumImpl extends WebTextDropDownListSeleniumImpl implements WebTextAutocomplete {

    // Actions

    @Override
    public WebTextAutocomplete executeAction(String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextAutocomplete executeInteraction(String interactionName, WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextAutocomplete should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebTextAutocomplete should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Clear

    @Override
    public WebTextAutocomplete clear() {
        runCheck(getEnvironment(), InvocationName.of(CLEAR_METHOD, this),
                () -> getActionImplementation(CLEAR_METHOD, Boolean.class).execute(this));
        return this;
    }

    // Click

    @Override
    public WebTextAutocomplete click() {
        super.click();
        return this;
    }

    // ClickToElement

    @Override
    public WebTextAutocomplete clickToElement(WebTextListFilter filter) {
        super.clickToElement(filter);
        return this;
    }

    // Close

    @Override
    public WebTextAutocomplete close() {
        super.close();
        return this;
    }

    // Get Color

    @Override
    public WebTextAutocomplete componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebTextAutocomplete componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Label

    @Override
    public WebTextAutocomplete shouldHaveLabel(StringValue expectedValue) {
        super.shouldHaveLabel(expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldHaveLabel(NumberValue<?> expectedValue) {
        super.shouldHaveLabel(expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotHaveLabel(StringValue expectedValue) {
        super.shouldNotHaveLabel(expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotHaveLabel(NumberValue<?> expectedValue) {
        super.shouldNotHaveLabel(expectedValue);
        return this;
    }

    // Get Location

    @Override
    public WebTextAutocomplete componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebTextAutocomplete componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // Get Text

    @Override
    public WebTextAutocomplete shouldHaveText(StringValue expectedValue) {
        super.shouldHaveText(expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldHaveText(NumberValue<?> expectedValue) {
        super.shouldHaveText(expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotHaveText(StringValue expectedValue) {
        super.shouldNotHaveText(expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotHaveText(NumberValue<?> expectedValue) {
        super.shouldNotHaveText(expectedValue);
        return this;
    }

    // HoverTo

    @Override
    public WebTextAutocomplete hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebTextAutocomplete shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebTextAutocomplete shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsOpen

    @Override
    public WebTextAutocomplete shouldBeOpen() {
        super.shouldBeOpen();
        return this;
    }

    @Override
    public WebTextAutocomplete shouldBeClosed() {
        super.shouldBeClosed();
        return this;
    }

    // IsPresent

    @Override
    public WebTextAutocomplete shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotBePresent() {
        super.shouldNotBePresent();
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

    // ScrollToElement

    @Override
    public WebTextAutocomplete scrollToElement(WebTextListFilter filter) {
        super.scrollToElement(filter);
        return this;
    }

    // SendKeys

    @Override
    public WebTextAutocomplete sendKeys(CharSequence... keys) {
        runCheck(getEnvironment(), InvocationName.of(SEND_KEYS_METHOD, this, keys),
                () -> getActionImplementation(SEND_KEYS_METHOD, Boolean.class).execute(this, List.of(keys)));
        return this;
    }

    // Size

    @Override
    public WebTextAutocomplete shouldHaveSize(NumberValue<Integer> expectedSize) {
        super.shouldHaveSize(expectedSize);
        return this;
    }

    // WebComponents

    @Override
    public WebTextAutocomplete componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebTextAutocomplete componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebTextAutocomplete shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextAutocomplete shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    //TODO: Implement
    @Override
    public JsonNode toJson() {
        return super.toJson();
    }

}
