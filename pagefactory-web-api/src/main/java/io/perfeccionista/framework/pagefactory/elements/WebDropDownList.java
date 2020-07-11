package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "self::node()//label")
public interface WebDropDownList extends WebList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    // Actions

    @Override
    WebDropDownList executeAction(String name, Object... args);

    @Override
    WebDropDownList executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebDropDownList should(WebAssertCondition assertCondition);

    @Override
    WebDropDownList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebDropDownList click();

    // ClickToElement

    @Override
    WebDropDownList clickToElement(WebListFilter filter); // Тут нужно еще скроллить к элементу

    // Close

    @Override
    WebDropDownList close();

    // Get Color

    @Override
    WebDropDownList componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebDropDownList componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebDropDownList componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebDropDownList componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebDropDownList shouldHaveLabel(StringValue expectedValue);

    @Override
    WebDropDownList shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebDropDownList shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebDropDownList shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebDropDownList componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebDropDownList componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebDropDownList componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebDropDownList componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebDropDownList shouldHaveText(StringValue expectedValue);

    @Override
    WebDropDownList shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebDropDownList shouldNotHaveText(StringValue expectedValue);

    @Override
    WebDropDownList shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebDropDownList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebDropDownList shouldBeDisplayed();

    @Override
    WebDropDownList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebDropDownList shouldBeInFocus();

    @Override
    WebDropDownList shouldNotBeInFocus();

    // IsOpen

    @Override
    WebDropDownList shouldBeOpen();

    @Override
    WebDropDownList shouldBeClosed();

    // IsPresent

    @Override
    WebDropDownList shouldBePresent();

    @Override
    WebDropDownList shouldNotBePresent();

    // Open

    @Override
    WebDropDownList open();

    // ScrollTo

    @Override
    WebDropDownList scrollTo();

    // ScrollToElement

    @Override
    WebDropDownList scrollToElement(WebListFilter filter);

    // Size

    @Override
    WebDropDownList shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebDropDownList componentShouldBePresent(String componentName);

    @Override
    WebDropDownList componentShouldNotBePresent(String componentName);

    @Override
    WebDropDownList componentShouldBeDisplayed(String componentName);

    @Override
    WebDropDownList componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebDropDownList shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebDropDownList shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebDropDownList shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebDropDownList shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
