package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SubmitAvailable;
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
@WebLocator(component = TEXT, xpath = "self::node()//input[@type = 'file']")
@WebLocator(component = INPUT, xpath = "self::node()//input[@type = 'file']")
@WebLocator(component = CLEAR, xpath = "self::node()//input[@type = 'file']")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'file']")
// TODO: Метод loadFile(Path filePath)
public interface WebFileInput extends WebChildElement,
        GetTextAvailable, SendKeysAvailable, ClearAvailable, SubmitAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions

    @Override
    WebFileInput executeAction(String name, Object... args);

    @Override
    WebFileInput executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebFileInput should(WebAssertCondition assertCondition);

    @Override
    WebFileInput should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebFileInput clear();

    // Get Color

    @Override
    WebFileInput componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebFileInput componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebFileInput componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebFileInput componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebFileInput shouldHaveLabel(StringValue expectedValue);

    @Override
    WebFileInput shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebFileInput shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebFileInput shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebFileInput componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebFileInput componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebFileInput componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebFileInput componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // GetText

    @Override
    WebFileInput shouldHaveText(StringValue expectedValue);

    @Override
    WebFileInput shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebFileInput shouldNotHaveText(StringValue expectedValue);

    @Override
    WebFileInput shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebFileInput hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebFileInput shouldBeDisplayed();

    @Override
    WebFileInput shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebFileInput shouldBeEnabled();

    @Override
    WebFileInput shouldBeDisabled();

    // IsInFocus

    @Override
    WebFileInput shouldBeInFocus();

    @Override
    WebFileInput shouldNotBeInFocus();

    // IsPresent

    @Override
    WebFileInput shouldBePresent();

    @Override
    WebFileInput shouldNotBePresent();

    // ScrollTo

    @Override
    WebFileInput scrollTo();

    // SendKeys

    @Override
    WebFileInput sendKeys(CharSequence... keys);

    // Submit

    @Override
    WebFileInput submit();

    // WebComponents

    @Override
    WebFileInput componentShouldBePresent(String componentName);

    @Override
    WebFileInput componentShouldNotBePresent(String componentName);

    @Override
    WebFileInput componentShouldBeDisplayed(String componentName);

    @Override
    WebFileInput componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebFileInput shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebFileInput shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebFileInput shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebFileInput shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
