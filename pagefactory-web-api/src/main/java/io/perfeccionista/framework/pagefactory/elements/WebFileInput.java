package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SubmitAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebFileInput extends WebChildElement,
        GetTextAvailable, SendKeysAvailable, ClearAvailable, SubmitAvailable, IsEnabledAvailable, GetLabelAvailable {

    @Override
    WebFileInput executeAction(String name, Object... args);

    @Override
    WebFileInput executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebFileInput clear();

    @Override
    WebFileInput hoverTo(boolean withOutOfBounds);

    @Override
    WebFileInput scrollTo();

    @Override
    WebFileInput sendKeys(CharSequence... keys);

    @Override
    WebFileInput submit();


    @Override
    WebFileInput shouldBePresent();

    @Override
    WebFileInput shouldNotBePresent();

    @Override
    WebFileInput shouldBeDisplayed();

    @Override
    WebFileInput shouldNotBeDisplayed();

    @Override
    WebFileInput shouldBeEnabled();

    @Override
    WebFileInput shouldBeDisabled();

    @Override
    WebFileInput shouldBeInFocus();

    @Override
    WebFileInput shouldNotBeInFocus();


    @Override
    WebFileInput shouldHaveText(StringValue stringValue);

    @Override
    WebFileInput shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebFileInput shouldNotHaveText(StringValue stringValue);

    @Override
    WebFileInput shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebFileInput shouldHaveLabel(StringValue stringValue);

    @Override
    WebFileInput shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebFileInput shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebFileInput shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebFileInput shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebFileInput shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebFileInput shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebFileInput shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebFileInput componentShouldBePresent(String componentName);

    @Override
    WebFileInput componentShouldNotBePresent(String componentName);

    @Override
    WebFileInput componentShouldBeDisplayed(String componentName);

    @Override
    WebFileInput componentShouldNotBeDisplayed(String componentName);

    @Override
    WebFileInput componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebFileInput componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebFileInput componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebFileInput componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
