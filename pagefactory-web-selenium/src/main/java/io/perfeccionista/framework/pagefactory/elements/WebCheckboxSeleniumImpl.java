package io.perfeccionista.framework.pagefactory.elements;

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
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;

public class WebCheckboxSeleniumImpl extends AbstractWebChildElement implements WebCheckbox {

    // Actions

    @Override
    public WebCheckbox executeAction(String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebCheckbox executeInteraction(String name, WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebCheckbox should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebCheckbox should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }


    // Click

    @Override
    public WebCheckbox click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebCheckbox componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebCheckbox componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebCheckbox componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebCheckbox componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
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
    public WebCheckbox shouldHaveLabel(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebCheckbox shouldHaveLabel(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebCheckbox shouldNotHaveLabel(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebCheckbox shouldNotHaveLabel(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    // Get Locations

    @Override
    public WebCheckbox componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebCheckbox componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebCheckbox componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebCheckbox componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebCheckbox hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebCheckbox shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebCheckbox shouldNotBeDisplayed() {
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
    public WebCheckbox shouldBeEnabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_ENABLED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class).execute(this));
        return this;
    }

    @Override
    public WebCheckbox shouldBeDisabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_DISABLED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class).execute(this));
        return this;
    }

    // IsInFocus

    @Override
    public WebCheckbox shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebCheckbox shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebCheckbox shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebCheckbox shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // IsSelected

    @Override
    public boolean isSelected() {
        return runCheck(getEnvironment(), InvocationName.of(IS_SELECTED_METHOD, this),
                () -> getActionImplementation(IS_SELECTED_METHOD, Boolean.class).execute(this));
    }

    @Override
    public WebCheckbox shouldBeSelected() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_SELECTED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_SELECTED_METHOD, Void.class).execute(this));
        return this;
    }

    @Override
    public WebCheckbox shouldNotBeSelected() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_BE_SELECTED_METHOD, this),
                () -> getActionImplementation(SHOULD_NOT_BE_SELECTED_METHOD, Void.class).execute(this));
        return this;
    }

    // ScrollTo

    @Override
    public WebCheckbox scrollTo() {
        super.scrollTo();
        return this;
    }

    // WebComponents

    @Override
    public WebCheckbox componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebCheckbox componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebCheckbox componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebCheckbox componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebCheckbox shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebCheckbox shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebCheckbox shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebCheckbox shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
