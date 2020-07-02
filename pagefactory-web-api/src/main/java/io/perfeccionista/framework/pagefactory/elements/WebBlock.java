package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebBlock extends WebChildElement, WebParentElement {

    // Actions

    @Override
    WebBlock executeAction(String name, Object... args);

    @Override
    WebBlock executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebBlock should(WebAssertCondition assertCondition);

    @Override
    WebBlock should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebBlock componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebBlock componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebBlock componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebBlock componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebBlock componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebBlock componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebBlock componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebBlock componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebBlock hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebBlock shouldBeDisplayed();

    @Override
    WebBlock shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebBlock shouldBeInFocus();

    @Override
    WebBlock shouldNotBeInFocus();

    // IsPresent

    @Override
    WebBlock shouldBePresent();

    @Override
    WebBlock shouldNotBePresent();

    // ScrollTo

    @Override
    WebBlock scrollTo();

    // WebComponents

    @Override
    WebBlock componentShouldBePresent(String componentName);

    @Override
    WebBlock componentShouldNotBePresent(String componentName);

    @Override
    WebBlock componentShouldBeDisplayed(String componentName);

    @Override
    WebBlock componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebBlock shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebBlock shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebBlock shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebBlock shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
