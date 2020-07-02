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
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
public interface WebTextDropDownList extends WebTextList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    // Actions

    @Override
    WebTextDropDownList executeAction(String name, Object... args);

    @Override
    WebTextDropDownList executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextDropDownList should(WebAssertCondition assertCondition);

    @Override
    WebTextDropDownList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebTextDropDownList click();

    // ClickToElement

    @Override
    WebTextDropDownList clickToElement(WebTextListFilter filter);

    // Close

    @Override
    WebTextDropDownList close();

    // Get Color

    @Override
    WebTextDropDownList componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebTextDropDownList componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTextDropDownList componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebTextDropDownList componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Label

    @Override
    WebTextDropDownList shouldHaveLabel(StringValue expectedValue);

    @Override
    WebTextDropDownList shouldHaveLabel(NumberValue<?> expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveLabel(StringValue expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveLabel(NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebTextDropDownList componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebTextDropDownList componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextDropDownList componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebTextDropDownList componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextDropDownList shouldHaveText(StringValue expectedValue);

    @Override
    WebTextDropDownList shouldHaveText(NumberValue<?> expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveText(StringValue expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveText(NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextDropDownList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextDropDownList shouldBeDisplayed();

    @Override
    WebTextDropDownList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextDropDownList shouldBeInFocus();

    @Override
    WebTextDropDownList shouldNotBeInFocus();

    // IsOpen

    @Override
    WebTextDropDownList shouldBeOpen();

    @Override
    WebTextDropDownList shouldBeClosed();

    // IsPresent

    @Override
    WebTextDropDownList shouldBePresent();

    @Override
    WebTextDropDownList shouldNotBePresent();

    // Open

    @Override
    WebTextDropDownList open();

    // ScrollTo

    @Override
    WebTextDropDownList scrollTo();

    // ScrollToElement

    @Override
    WebTextDropDownList scrollToElement(WebTextListFilter filter);

    // Size

    @Override
    WebTextDropDownList shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextDropDownList componentShouldBePresent(String componentName);

    @Override
    WebTextDropDownList componentShouldNotBePresent(String componentName);

    @Override
    WebTextDropDownList componentShouldBeDisplayed(String componentName);

    @Override
    WebTextDropDownList componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTextDropDownList shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextDropDownList shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTextDropDownList shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextDropDownList shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
