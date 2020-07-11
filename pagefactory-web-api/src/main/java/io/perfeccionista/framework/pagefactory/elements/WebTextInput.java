package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;


@WebLocator(component = LABEL, xpath = "self::node()//label")
@WebLocator(component = TEXT, xpath = "self::node()//input[@type = 'text']")
@WebLocator(component = INPUT, xpath = "self::node()//input[@type = 'text']")
@WebLocator(component = CLEAR, xpath = "self::node()//input[@type = 'text']")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'text']")
public interface WebTextInput extends WebChildElement,
        GetTextAvailable, GetLabelAvailable, ClickAvailable, SendKeysAvailable, ClearAvailable, IsEnabledAvailable {

    // Actions

    @Override
    WebTextInput executeAction(String name, Object... args);

    @Override
    WebTextInput executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextInput should(WebAssertCondition assertCondition);

    @Override
    WebTextInput should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebTextInput clear();

    // Click

    @Override
    WebTextInput click();

    // Get Color

    @Override
    WebTextInput componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebTextInput componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTextInput componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebTextInput componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebTextInput shouldHaveLabel(StringValue expectedValue);

    @Override
    WebTextInput shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebTextInput shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebTextInput shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebTextInput componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebTextInput componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextInput componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebTextInput componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextInput shouldHaveText(StringValue expectedValue);

    @Override
    WebTextInput shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebTextInput shouldNotHaveText(StringValue expectedValue);

    @Override
    WebTextInput shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextInput hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextInput shouldBeDisplayed();

    @Override
    WebTextInput shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebTextInput shouldBeEnabled();

    @Override
    WebTextInput shouldBeDisabled();

    // IsInFocus

    @Override
    WebTextInput shouldBeInFocus();

    @Override
    WebTextInput shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextInput shouldBePresent();

    @Override
    WebTextInput shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextInput scrollTo();

    // SendKeys

    @Override
    WebTextInput sendKeys(CharSequence... keys);

    // WebComponent

    @Override
    WebTextInput componentShouldBePresent(String componentName);

    @Override
    WebTextInput componentShouldNotBePresent(String componentName);

    @Override
    WebTextInput componentShouldBeDisplayed(String componentName);

    @Override
    WebTextInput componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTextInput shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextInput shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTextInput shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextInput shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
