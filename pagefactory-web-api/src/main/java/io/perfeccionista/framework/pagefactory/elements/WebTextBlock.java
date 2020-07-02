package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebTextBlock extends WebChildElement,
        GetTextAvailable {

    // Actions

    @Override
    WebTextBlock executeAction(String name, Object... args);

    @Override
    WebTextBlock executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextBlock should(WebAssertCondition assertCondition);

    @Override
    WebTextBlock should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebTextBlock componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebTextBlock componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTextBlock componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebTextBlock componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTextBlock componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebTextBlock componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextBlock componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebTextBlock componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextBlock shouldHaveText(StringValue expectedValue);

    @Override
    WebTextBlock shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebTextBlock shouldNotHaveText(StringValue expectedValue);

    @Override
    WebTextBlock shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextBlock hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextBlock shouldBeDisplayed();

    @Override
    WebTextBlock shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextBlock shouldBeInFocus();

    @Override
    WebTextBlock shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextBlock shouldBePresent();

    @Override
    WebTextBlock shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextBlock scrollTo();

    // WebComponent

    @Override
    WebTextBlock componentShouldBePresent(String componentName);

    @Override
    WebTextBlock componentShouldNotBePresent(String componentName);

    @Override
    WebTextBlock componentShouldBeDisplayed(String componentName);

    @Override
    WebTextBlock componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTextBlock shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextBlock shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTextBlock shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextBlock shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
