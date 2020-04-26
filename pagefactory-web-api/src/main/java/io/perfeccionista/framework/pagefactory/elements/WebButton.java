package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebButton extends WebLink {

    @Override
    WebButton executeAction(String name, Object... args);

    @Override
    WebButton executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebButton click();

    @Override
    WebButton hoverTo(boolean withOutOfBounds);

    @Override
    WebButton scrollTo();


    @Override
    WebButton shouldBePresent();

    @Override
    WebButton shouldNotBePresent();

    @Override
    WebButton shouldBeDisplayed();

    @Override
    WebButton shouldNotBeDisplayed();

    @Override
    WebButton shouldBeInFocus();

    @Override
    WebButton shouldNotBeInFocus();


    @Override
    WebButton shouldHaveText(StringValue stringValue);

    @Override
    WebButton shouldHaveText(NumberValue<?> stringValue);

    @Override
    WebButton shouldNotHaveText(StringValue stringValue);

    @Override
    WebButton shouldNotHaveText(NumberValue<?> stringValue);

    @Override
    WebButton shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebButton shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebButton shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebButton shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebButton componentShouldBePresent(String componentName);

    @Override
    WebButton componentShouldNotBePresent(String componentName);

    @Override
    WebButton componentShouldBeDisplayed(String componentName);

    @Override
    WebButton componentShouldNotBeDisplayed(String componentName);

    @Override
    WebButton componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebButton componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebButton componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebButton componentShouldNotLooksLike(String componentName, Screenshot screenshot);

}
