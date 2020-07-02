package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

// TODO: Добавить TextBlockExtractor/LinkExtractor
@WebLocator(component = LI, xpath = ".//li", single = false)
public interface WebTextList extends WebChildElement,
        ScrollToElementAvailable<WebTextListFilter>, ClickToElementAvailable<WebTextListFilter>, SizeAvailable {

    WebTextListFilterResult filter(WebTextListFilter filter);

    MultipleResult<String> extractAll();

    // Actions

    @Override
    WebTextList executeAction(String name, Object... args);

    @Override
    WebTextList executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextList should(WebAssertCondition assertCondition);

    @Override
    WebTextList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // ClickToElement

    @Override
    WebTextList clickToElement(WebTextListFilter filter);

    // Get Color

    @Override
    WebTextList componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebTextList componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTextList componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebTextList componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTextList componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebTextList componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextList componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebTextList componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebTextList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextList shouldBeDisplayed();

    @Override
    WebTextList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextList shouldBeInFocus();

    @Override
    WebTextList shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextList shouldBePresent();

    @Override
    WebTextList shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextList scrollTo();

    // ScrollToElement

    @Override
    WebTextList scrollToElement(WebTextListFilter filter);

    // Size

    @Override
    WebTextList shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextList componentShouldBePresent(String componentName);

    @Override
    WebTextList componentShouldNotBePresent(String componentName);

    @Override
    WebTextList componentShouldBeDisplayed(String componentName);

    @Override
    WebTextList componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTextList shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextList shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTextList shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextList shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}