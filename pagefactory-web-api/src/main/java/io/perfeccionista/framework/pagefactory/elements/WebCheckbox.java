package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.SELECTED;

@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@WebLocator(component = SELECTED, xpath = "self::node()//input[@type = 'checkbox'] | self::node()")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'checkbox'] | self::node()")
public interface WebCheckbox extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions

    @Override
    WebCheckbox executeAction(String name, Object... args);

    @Override
    WebCheckbox executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebCheckbox should(WebAssertCondition assertCondition);

    @Override
    WebCheckbox should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebCheckbox click();

    // Get Color

    @Override
    WebCheckbox componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebCheckbox componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebCheckbox componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebCheckbox componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebCheckbox shouldHaveLabel(StringValue expectedValue);

    @Override
    WebCheckbox shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebCheckbox shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebCheckbox shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebCheckbox componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebCheckbox componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebCheckbox componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebCheckbox componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebCheckbox hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebCheckbox shouldBeDisplayed();

    @Override
    WebCheckbox shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebCheckbox shouldBeEnabled();

    @Override
    WebCheckbox shouldBeDisabled();

    // IsInFocus

    @Override
    WebCheckbox shouldBeInFocus();

    @Override
    WebCheckbox shouldNotBeInFocus();

    // IsPresent

    @Override
    WebCheckbox shouldBePresent();

    @Override
    WebCheckbox shouldNotBePresent();

    // IsSelected

    @Override
    WebCheckbox shouldBeSelected();

    @Override
    WebCheckbox shouldNotBeSelected();

    // ScrollTo

    @Override
    WebCheckbox scrollTo();

    // WebComponents

    @Override
    WebCheckbox componentShouldBePresent(String componentName);

    @Override
    WebCheckbox componentShouldNotBePresent(String componentName);

    @Override
    WebCheckbox componentShouldBeDisplayed(String componentName);

    @Override
    WebCheckbox componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebCheckbox shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebCheckbox shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebCheckbox shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebCheckbox shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
