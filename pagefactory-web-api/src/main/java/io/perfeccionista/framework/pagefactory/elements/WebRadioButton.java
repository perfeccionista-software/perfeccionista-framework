package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.ParentInfoAvailable;
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

@WebLocator(component = LABEL, xpath = "self::node()//label")
@WebLocator(component = SELECTED, xpath = "self::node()//input[@type = 'radio']")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'radio']")
public interface WebRadioButton extends WebChildElement, ParentInfoAvailable,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions

    @Override
    WebRadioButton executeAction(String name, Object... args);

    @Override
    WebRadioButton executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebRadioButton should(WebAssertCondition assertCondition);

    @Override
    WebRadioButton should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebRadioButton click();

    // Get Color

    @Override
    WebRadioButton componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebRadioButton componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebRadioButton componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebRadioButton componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebRadioButton shouldHaveLabel(StringValue expectedValue);

    @Override
    WebRadioButton shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebRadioButton shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebRadioButton shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebRadioButton componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebRadioButton componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebRadioButton componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebRadioButton componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebRadioButton hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebRadioButton shouldBeDisplayed();

    @Override
    WebRadioButton shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebRadioButton shouldBeEnabled();

    @Override
    WebRadioButton shouldBeDisabled();

    // IsInFocus

    @Override
    WebRadioButton shouldBeInFocus();

    @Override
    WebRadioButton shouldNotBeInFocus();

    // IsPresent

    @Override
    WebRadioButton shouldBePresent();

    @Override
    WebRadioButton shouldNotBePresent();

    // IsSelected

    @Override
    WebRadioButton shouldBeSelected();

    @Override
    WebRadioButton shouldNotBeSelected();

    // ScrollTo

    @Override
    WebRadioButton scrollTo();

    // WebComponents

    @Override
    WebRadioButton componentShouldBePresent(String componentName);

    @Override
    WebRadioButton componentShouldNotBePresent(String componentName);

    @Override
    WebRadioButton componentShouldBeDisplayed(String componentName);

    @Override
    WebRadioButton componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebRadioButton shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebRadioButton shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebRadioButton shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebRadioButton shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
