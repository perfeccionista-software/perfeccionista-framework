package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebLink extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    @Override
    WebLink executeAction(String name, Object... args);

    @Override
    WebLink executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebLink click();

    @Override
    WebLink hoverTo(boolean withOutOfBounds);

    @Override
    WebLink scrollTo();


    @Override
    WebLink shouldBePresent();

    @Override
    WebLink shouldNotBePresent();

    @Override
    WebLink shouldBeDisplayed();

    @Override
    WebLink shouldNotBeDisplayed();

    @Override
    WebLink shouldBeInFocus();

    @Override
    WebLink shouldNotBeInFocus();


    @Override
    WebLink shouldHaveText(StringValue stringValue);

    @Override
    WebLink shouldHaveText(NumberValue<?> stringValue);

    @Override
    WebLink shouldNotHaveText(StringValue stringValue);

    @Override
    WebLink shouldNotHaveText(NumberValue<?> stringValue);

    @Override
    WebLink shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebLink shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebLink shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebLink shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebLink componentShouldBePresent(String componentName);

    @Override
    WebLink componentShouldNotBePresent(String componentName);

    @Override
    WebLink componentShouldBeDisplayed(String componentName);

    @Override
    WebLink componentShouldNotBeDisplayed(String componentName);

    @Override
    WebLink componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebLink componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebLink componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebLink componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebLink componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebLink componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}