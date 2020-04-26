package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebTextInput extends WebChildElement,
        ClickAvailable, GetTextAvailable, SendKeysAvailable, ClearAvailable, IsEnabledAvailable, GetLabelAvailable {

    @Override
    WebTextInput executeAction(String name, Object... args);

    @Override
    WebTextInput executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTextInput clear();

    @Override
    WebTextInput click();

    @Override
    WebTextInput hoverTo(boolean withOutOfBounds);

    @Override
    WebTextInput scrollTo();

    @Override
    WebTextInput sendKeys(CharSequence... keys);


    @Override
    WebTextInput shouldBePresent();

    @Override
    WebTextInput shouldNotBePresent();

    @Override
    WebTextInput shouldBeDisplayed();

    @Override
    WebTextInput shouldNotBeDisplayed();

    @Override
    WebTextInput shouldBeEnabled();

    @Override
    WebTextInput shouldBeDisabled();

    @Override
    WebTextInput shouldBeInFocus();

    @Override
    WebTextInput shouldNotBeInFocus();


    @Override
    WebTextInput shouldHaveText(StringValue stringValue);

    @Override
    WebTextInput shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebTextInput shouldNotHaveText(StringValue stringValue);

    @Override
    WebTextInput shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebTextInput shouldHaveLabel(StringValue stringValue);

    @Override
    WebTextInput shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebTextInput shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebTextInput shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebTextInput shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextInput shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTextInput shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextInput shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTextInput componentShouldBePresent(String componentName);

    @Override
    WebTextInput componentShouldNotBePresent(String componentName);

    @Override
    WebTextInput componentShouldBeDisplayed(String componentName);

    @Override
    WebTextInput componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTextInput componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextInput componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextInput componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTextInput componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
