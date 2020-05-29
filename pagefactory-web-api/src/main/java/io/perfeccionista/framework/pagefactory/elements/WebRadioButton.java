package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ParentInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebRadioButton extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    ParentInfo getParentInfo();

    @Override
    WebRadioButton executeAction(String name, Object... args);

    @Override
    WebRadioButton executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebRadioButton click();

    @Override
    WebRadioButton hoverTo(boolean withOutOfBounds);

    @Override
    WebRadioButton scrollTo();


    @Override
    WebRadioButton shouldBePresent();

    @Override
    WebRadioButton shouldNotBePresent();

    @Override
    WebRadioButton shouldBeDisplayed();

    @Override
    WebRadioButton shouldNotBeDisplayed();

    @Override
    WebRadioButton shouldBeEnabled();

    @Override
    WebRadioButton shouldBeDisabled();

    @Override
    WebRadioButton shouldBeInFocus();

    @Override
    WebRadioButton shouldNotBeInFocus();

    @Override
    WebRadioButton shouldBeSelected();

    @Override
    WebRadioButton shouldNotBeSelected();


    @Override
    WebRadioButton shouldHaveLabel(StringValue stringValue);

    @Override
    WebRadioButton shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebRadioButton shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebRadioButton shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebRadioButton shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebRadioButton shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebRadioButton shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebRadioButton shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebRadioButton componentShouldBePresent(String componentName);

    @Override
    WebRadioButton componentShouldNotBePresent(String componentName);

    @Override
    WebRadioButton componentShouldBeDisplayed(String componentName);

    @Override
    WebRadioButton componentShouldNotBeDisplayed(String componentName);

    @Override
    WebRadioButton componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebRadioButton componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebRadioButton componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebRadioButton componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebRadioButton componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebRadioButton componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
