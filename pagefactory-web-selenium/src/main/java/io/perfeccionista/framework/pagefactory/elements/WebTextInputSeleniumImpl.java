package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;

public class WebTextInputSeleniumImpl extends AbstractWebChildElement implements WebTextInput {

    // Actions

    @Override
    public WebTextInput executeAction(String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextInput executeInteraction(String interactionName, WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextInput should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebTextInput should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Clear

    @Override
    public WebTextInput clear() {
        runCheck(getEnvironment(), InvocationName.of(CLEAR_METHOD, this),
                () -> getActionImplementation(CLEAR_METHOD, Void.class).execute(this));
        return this;
    }

    // Click

    @Override
    public WebTextInput click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebTextInput componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebTextInput componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebTextInput componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebTextInput componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Label

    @Override
    public String getLabel() {
        return runCheck(getEnvironment(), InvocationName.of(GET_LABEL_METHOD, this),
                () -> getActionImplementation(GET_LABEL_METHOD, String.class).execute(this));
    }

    @Override
    public WebTextInput shouldHaveLabel(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebTextInput shouldHaveLabel(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebTextInput shouldNotHaveLabel(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebTextInput shouldNotHaveLabel(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    // Get Location

    @Override
    public WebTextInput componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebTextInput componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebTextInput componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebTextInput componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // Get Text

    @Override
    public String getText() {
        return runCheck(getEnvironment(), InvocationName.of(GET_TEXT_METHOD, this),
                () -> getActionImplementation(GET_TEXT_METHOD, String.class).execute(this));
    }

    @Override
    public WebTextInput shouldHaveText(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_TEXT_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_TEXT_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebTextInput shouldHaveText(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_NUMBER_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_NUMBER_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebTextInput shouldNotHaveText(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_TEXT_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_TEXT_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebTextInput shouldNotHaveText(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_NUMBER_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_NUMBER_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    // HoverTo

    @Override
    public WebTextInput hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed


    @Override
    public WebTextInput shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebTextInput shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        return runCheck(getEnvironment(), InvocationName.of(IS_ENABLED_METHOD, this),
                () -> getActionImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this));
    }

    @Override
    public WebTextInput shouldBeEnabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_ENABLED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class).execute(this));
        return this;
    }

    @Override
    public WebTextInput shouldBeDisabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_DISABLED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class).execute(this));
        return this;
    }

    // IsInFocus

    @Override
    public WebTextInput shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebTextInput shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebTextInput shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebTextInput shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // ScrollTo

    @Override
    public WebTextInput scrollTo() {
        super.scrollTo();
        return this;
    }

    // SendKeys

    @Override
    public WebTextInput sendKeys(CharSequence... keys) {
        runCheck(getEnvironment(), InvocationName.of(SEND_KEYS_METHOD, this, keys),
                () -> getActionImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys));
        return this;
    }

    // WebComponents

    @Override
    public WebTextInput componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebTextInput componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebTextInput componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebTextInput componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebTextInput shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextInput shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextInput shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextInput shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    // TODO: Implement
    @Override
    public JsonNode toJson() {
        return super.toJson();
    }

}
