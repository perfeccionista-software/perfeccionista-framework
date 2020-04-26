package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebCheckbox extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    @Override
    WebCheckbox executeAction(String name, Object... args);

    @Override
    WebCheckbox executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebCheckbox click();

    @Override
    WebCheckbox hoverTo(boolean withOutOfBounds);

    @Override
    WebCheckbox scrollTo();


    @Override
    WebCheckbox shouldBePresent();

    @Override
    WebCheckbox shouldNotBePresent();

    @Override
    WebCheckbox shouldBeDisplayed();

    @Override
    WebCheckbox shouldNotBeDisplayed();

    @Override
    WebCheckbox shouldBeEnabled();

    @Override
    WebCheckbox shouldBeDisabled();

    @Override
    WebCheckbox shouldBeInFocus();

    @Override
    WebCheckbox shouldNotBeInFocus();

    @Override
    WebCheckbox shouldBeSelected();

    @Override
    WebCheckbox shouldNotBeSelected();


    @Override
    WebCheckbox shouldHaveLabel(StringValue stringValue);

    @Override
    WebCheckbox shouldHaveLabel(NumberValue<?> stringValue);

    @Override
    WebCheckbox shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebCheckbox shouldNotHaveLabel(NumberValue<?> stringValue);

    @Override
    WebCheckbox shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebCheckbox shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebCheckbox shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebCheckbox shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebCheckbox componentShouldBePresent(String componentName);

    @Override
    WebCheckbox componentShouldNotBePresent(String componentName);

    @Override
    WebCheckbox componentShouldBeDisplayed(String componentName);

    @Override
    WebCheckbox componentShouldNotBeDisplayed(String componentName);

    @Override
    WebCheckbox componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebCheckbox componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebCheckbox componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebCheckbox componentShouldNotLooksLike(String componentName, Screenshot screenshot);

}
