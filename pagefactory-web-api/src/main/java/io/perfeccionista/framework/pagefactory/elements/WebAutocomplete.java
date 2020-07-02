package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

@WebLocator(component = TEXT, xpath = "self::node()//input[@type = 'text'] | self::node()")
@WebLocator(component = INPUT, xpath = "self::node()//input[@type = 'text'] | self::node()")
@WebLocator(component = CLEAR, xpath = "self::node()//input[@type = 'text'] | self::node()")
public interface WebAutocomplete extends WebDropDownList,
        SendKeysAvailable, ClearAvailable {

    // Actions

    @Override
    WebAutocomplete executeAction(String name, Object... args);

    @Override
    WebAutocomplete executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebAutocomplete should(WebAssertCondition assertCondition);

    @Override
    WebAutocomplete should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebAutocomplete clear();

    // Click

    @Override
    WebAutocomplete click();

    // ClickToElement

    @Override
    WebAutocomplete clickToElement(WebListFilter filter); // Тут нужно еще скроллить к элементу

    // Close

    @Override
    WebAutocomplete close();

    // Get Color

    @Override
    WebAutocomplete componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebAutocomplete componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebAutocomplete componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebAutocomplete componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebAutocomplete shouldHaveLabel(StringValue expectedValue);

    @Override
    WebAutocomplete shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebAutocomplete shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebAutocomplete shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebAutocomplete componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebAutocomplete componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebAutocomplete componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebAutocomplete componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebAutocomplete shouldHaveText(StringValue expectedValue);

    @Override
    WebAutocomplete shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebAutocomplete shouldNotHaveText(StringValue expectedValue);

    @Override
    WebAutocomplete shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebAutocomplete hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebAutocomplete shouldBeDisplayed();

    @Override
    WebAutocomplete shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebAutocomplete shouldBeInFocus();

    @Override
    WebAutocomplete shouldNotBeInFocus();

    // IsOpen

    @Override
    WebAutocomplete shouldBeOpen();

    @Override
    WebAutocomplete shouldBeClosed();

    // IsPresent

    @Override
    WebAutocomplete shouldBePresent();

    @Override
    WebAutocomplete shouldNotBePresent();

    // Open

    @Override
    WebAutocomplete open();

    // ScrollTo

    @Override
    WebAutocomplete scrollTo();

    // ScrollToElement

    @Override
    WebAutocomplete scrollToElement(WebListFilter filter);

    // SendKeys

    @Override
    WebAutocomplete sendKeys(CharSequence... keys);

    // Size

    @Override
    WebAutocomplete shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebAutocomplete componentShouldBePresent(String componentName);

    @Override
    WebAutocomplete componentShouldNotBePresent(String componentName);

    @Override
    WebAutocomplete componentShouldBeDisplayed(String componentName);

    @Override
    WebAutocomplete componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebAutocomplete shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebAutocomplete shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebAutocomplete shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebAutocomplete shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
