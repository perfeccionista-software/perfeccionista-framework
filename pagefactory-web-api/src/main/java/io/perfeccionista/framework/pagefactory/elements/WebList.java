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
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

@WebLocator(component = LI, xpath = ".//li", single = false)
public interface WebList extends WebChildElement,
        ScrollToElementAvailable<WebListFilter>, ClickToElementAvailable<WebListFilter>, SizeAvailable {

    WebListFilterResult filter(WebListFilter filter);

    <V> MultipleResult<V> extractAll(WebListBlockValueExtractor<V> extractor);

    // Actions

    @Override
    WebList executeAction(String name, Object... args);

    @Override
    WebList executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebList should(WebAssertCondition assertCondition);

    @Override
    WebList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // ClickToElement

    @Override
    WebList clickToElement(WebListFilter filter); // Тут нужно еще скроллить к элементу

    // Get Color

    @Override
    WebList componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebList componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebList componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebList componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebList componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebList componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebList componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebList componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebList shouldBeDisplayed();

    @Override
    WebList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebList shouldBeInFocus();

    @Override
    WebList shouldNotBeInFocus();

    // IsPresent

    @Override
    WebList shouldBePresent();

    @Override
    WebList shouldNotBePresent();

    // ScrollTo

    @Override
    WebList scrollTo();

    // ScrollToElement

    @Override
    WebList scrollToElement(WebListFilter filter);

    // Size

    @Override
    WebList shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebList componentShouldBePresent(String componentName);

    @Override
    WebList componentShouldNotBePresent(String componentName);

    @Override
    WebList componentShouldBeDisplayed(String componentName);

    @Override
    WebList componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebList shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebList shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebList shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebList shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
