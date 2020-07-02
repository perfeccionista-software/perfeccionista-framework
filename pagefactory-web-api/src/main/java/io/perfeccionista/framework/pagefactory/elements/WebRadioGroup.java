package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;

@WebLocator(component = RADIO, xpath = ".//input", single = false)
public interface WebRadioGroup extends WebChildElement,
        ScrollToElementAvailable<WebRadioButtonFilter>, SizeAvailable {

    WebRadioButtonFilterResult filter(WebRadioButtonFilter filter);

    <V> MultipleResult<V> extractAll(WebRadioButtonValueExtractor<V> extractor);

    WebRadioButton getSelected();

    WebRadioButton getByLabel(StringValue label);

    WebRadioButton getByIndex(NumberValue<Integer> index);

    // Actions

    @Override
    WebRadioGroup executeAction(String name, Object... args);

    @Override
    WebRadioGroup executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebRadioGroup should(WebAssertCondition assertCondition);

    @Override
    WebRadioGroup should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebRadioGroup componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebRadioGroup componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebRadioGroup componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebRadioGroup componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebRadioGroup componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebRadioGroup componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebRadioGroup componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebRadioGroup componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebRadioGroup hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebRadioGroup shouldBeDisplayed();

    @Override
    WebRadioGroup shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebRadioGroup shouldBeInFocus();

    @Override
    WebRadioGroup shouldNotBeInFocus();

    // IsPresent

    @Override
    WebRadioGroup shouldBePresent();

    @Override
    WebRadioGroup shouldNotBePresent();

    // ScrollTo

    @Override
    WebRadioGroup scrollTo();

    // ScrollToElement

    @Override
    WebRadioGroup scrollToElement(WebRadioButtonFilter filter);

    // Size

    @Override
    WebRadioGroup shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebRadioGroup componentShouldBePresent(String componentName);

    @Override
    WebRadioGroup componentShouldNotBePresent(String componentName);

    @Override
    WebRadioGroup componentShouldBeDisplayed(String componentName);

    @Override
    WebRadioGroup componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebRadioGroup shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebRadioGroup shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebRadioGroup shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebRadioGroup shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
