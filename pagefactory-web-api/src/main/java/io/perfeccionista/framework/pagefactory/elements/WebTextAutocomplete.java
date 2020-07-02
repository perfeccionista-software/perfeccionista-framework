package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

@WebLocator(component = TEXT, xpath = "self::node()//input[@type = 'text'] | self::node()")
@WebLocator(component = INPUT, xpath = "self::node()//input[@type = 'text'] | self::node()")
@WebLocator(component = CLEAR, xpath = "self::node()//input[@type = 'text'] | self::node()")
public interface WebTextAutocomplete extends WebTextDropDownList,
        SendKeysAvailable, ClearAvailable {

    // Actions

    @Override
    WebTextAutocomplete executeAction(String name, Object... args);

    @Override
    WebTextAutocomplete executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextAutocomplete should(WebAssertCondition assertCondition);

    @Override
    WebTextAutocomplete should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebTextAutocomplete clear();

    // Click

    @Override
    WebTextAutocomplete click();

    // ClickToElement

    @Override
    WebTextAutocomplete clickToElement(WebTextListFilter filter);

    // Close

    @Override
    WebTextAutocomplete close();

    // Get Color

    @Override
    WebTextAutocomplete componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebTextAutocomplete componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTextAutocomplete componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebTextAutocomplete componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebTextAutocomplete shouldHaveLabel(StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebTextAutocomplete componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebTextAutocomplete componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextAutocomplete componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebTextAutocomplete componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextAutocomplete shouldHaveText(StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveText(StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextAutocomplete hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextAutocomplete shouldBeDisplayed();

    @Override
    WebTextAutocomplete shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextAutocomplete shouldBeInFocus();

    @Override
    WebTextAutocomplete shouldNotBeInFocus();

    // IsOpen

    @Override
    WebTextAutocomplete shouldBeOpen();

    @Override
    WebTextAutocomplete shouldBeClosed();

    // IsPresent

    @Override
    WebTextAutocomplete shouldBePresent();

    @Override
    WebTextAutocomplete shouldNotBePresent();

    // Open

    @Override
    WebTextAutocomplete open();

    // ScrollTo

    @Override
    WebTextAutocomplete scrollTo();

    // ScrollToElement

    @Override
    WebTextAutocomplete scrollToElement(WebTextListFilter filter);

    // SendKeys

    @Override
    WebTextAutocomplete sendKeys(CharSequence... keys);

    // Size

    @Override
    WebTextAutocomplete shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextAutocomplete componentShouldBePresent(String componentName);

    @Override
    WebTextAutocomplete componentShouldNotBePresent(String componentName);

    @Override
    WebTextAutocomplete componentShouldBeDisplayed(String componentName);

    @Override
    WebTextAutocomplete componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTextAutocomplete shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTextAutocomplete shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);
}
