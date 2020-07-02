package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
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

public class WebRadioButtonSeleniumImpl extends AbstractWebChildElement implements WebRadioButton {

    protected WebParentInfo parentInfo;

    @Override
    public WebParentInfo getParentInfo() {
        return parentInfo;
    }

    // Actions

    @Override
    public WebRadioButton executeAction(String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebRadioButton executeInteraction(String name, WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebRadioButton should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebRadioButton should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Click

    @Override
    public WebRadioButton click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebRadioButton componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebRadioButton componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
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
    public WebRadioButton shouldHaveLabel(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebRadioButton shouldHaveLabel(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebRadioButton shouldNotHaveLabel(StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    @Override
    public WebRadioButton shouldNotHaveLabel(NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class).execute(this, expectedValue));
        return this;
    }

    // Get Location

    @Override
    public WebRadioButton componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebRadioButton componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebRadioButton hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebRadioButton shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebRadioButton shouldNotBeDisplayed() {
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
    public WebRadioButton shouldBeEnabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_ENABLED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class).execute(this));
        return this;
    }

    @Override
    public WebRadioButton shouldBeDisabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_DISABLED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class).execute(this));
        return this;
    }

    // IsInFocus

    @Override
    public WebRadioButton shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebRadioButton shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebRadioButton shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebRadioButton shouldNotBePresent() {
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
    public WebRadioButton shouldBeSelected() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_SELECTED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_SELECTED_METHOD, Void.class).execute(this));
        return this;
    }

    @Override
    public WebRadioButton shouldNotBeSelected() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_BE_SELECTED_METHOD, this),
                () -> getActionImplementation(SHOULD_NOT_BE_SELECTED_METHOD, Void.class).execute(this));
        return this;
    }

    // ScrollTo

    @Override
    public WebRadioButton scrollTo() {
        super.scrollTo();
        return this;
    }

    // WebComponents

    @Override
    public WebRadioButton componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioButton componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebRadioButton shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioButton shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioButton shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioButton shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
