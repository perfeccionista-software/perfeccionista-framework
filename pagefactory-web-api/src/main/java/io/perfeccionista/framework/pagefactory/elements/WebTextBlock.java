package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebTextBlock extends WebChildElement,
        GetTextAvailable {

    @Override
    WebTextBlock executeAction(String name, Object... args);

    @Override
    WebTextBlock executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTextBlock hoverTo(boolean withOutOfBounds);

    @Override
    WebTextBlock scrollTo();


    @Override
    WebTextBlock shouldBePresent();

    @Override
    WebTextBlock shouldNotBePresent();

    @Override
    WebTextBlock shouldBeDisplayed();

    @Override
    WebTextBlock shouldNotBeDisplayed();

    @Override
    WebTextBlock shouldBeInFocus();

    @Override
    WebTextBlock shouldNotBeInFocus();


    @Override
    WebTextBlock shouldHaveText(StringValue stringValue);

    @Override
    WebTextBlock shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebTextBlock shouldNotHaveText(StringValue stringValue);

    @Override
    WebTextBlock shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebTextBlock shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextBlock shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTextBlock shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextBlock shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTextBlock componentShouldBePresent(String componentName);

    @Override
    WebTextBlock componentShouldNotBePresent(String componentName);

    @Override
    WebTextBlock componentShouldBeDisplayed(String componentName);

    @Override
    WebTextBlock componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTextBlock componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebTextBlock componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebTextBlock componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebTextBlock componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebTextBlock componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTextBlock componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
