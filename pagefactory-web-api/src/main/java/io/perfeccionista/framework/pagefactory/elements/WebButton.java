package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebButton extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    // Actions

    @Override
    WebButton executeAction(String name, Object... args);

    @Override
    WebButton executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebButton should(WebAssertCondition assertCondition);

    @Override
    WebButton should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebButton click();

    // Get Color

    @Override
    WebButton componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebButton componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebButton componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebButton componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebButton componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebButton componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebButton componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebButton componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebButton shouldHaveText(StringValue expectedValue);

    @Override
    WebButton shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebButton shouldNotHaveText(StringValue expectedValue);

    @Override
    WebButton shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebButton hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebButton shouldBeDisplayed();

    @Override
    WebButton shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebButton shouldBeInFocus();

    @Override
    WebButton shouldNotBeInFocus();

    // IsPresent

    @Override
    WebButton shouldBePresent();

    @Override
    WebButton shouldNotBePresent();

    // ScrollTo

    @Override
    WebButton scrollTo();

    // WebComponents

    @Override
    WebButton componentShouldBePresent(String componentName);

    @Override
    WebButton componentShouldNotBePresent(String componentName);

    @Override
    WebButton componentShouldBeDisplayed(String componentName);

    @Override
    WebButton componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebButton shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebButton shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebButton shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebButton shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
