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


public interface WebLink extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    // Actions

    @Override
    WebLink executeAction(String name, Object... args);

    @Override
    WebLink executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebLink should(WebAssertCondition assertCondition);

    @Override
    WebLink should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebLink click();

    // Get Color

    @Override
    WebLink componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebLink componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebLink componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebLink componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebLink componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebLink componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebLink componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebLink componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebLink shouldHaveText(StringValue expectedValue);

    @Override
    WebLink shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebLink shouldNotHaveText(StringValue expectedValue);

    @Override
    WebLink shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebLink hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebLink shouldBeDisplayed();

    @Override
    WebLink shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebLink shouldBeInFocus();

    @Override
    WebLink shouldNotBeInFocus();

    // IsPresent

    @Override
    WebLink shouldBePresent();

    @Override
    WebLink shouldNotBePresent();

    // ScrollTo

    @Override
    WebLink scrollTo();

    // WebComponents

    @Override
    WebLink componentShouldBePresent(String componentName);

    @Override
    WebLink componentShouldNotBePresent(String componentName);

    @Override
    WebLink componentShouldBeDisplayed(String componentName);

    @Override
    WebLink componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebLink shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebLink shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebLink shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebLink shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}