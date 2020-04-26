package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebAutocomplete extends WebDropDownList,
        SendKeysAvailable, ClearAvailable {

    @Override
    WebAutocomplete executeAction(String name, Object... args);

    @Override
    WebAutocomplete executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebAutocomplete clear();

    @Override
    WebAutocomplete click();

    @Override
    WebAutocomplete open();

    @Override
    WebAutocomplete close();

    @Override
    WebAutocomplete hoverTo(boolean withOutOfBounds);

    @Override
    WebAutocomplete scrollTo();

    @Override
    WebAutocomplete scrollToElement(WebListFilter filter);

    @Override
    WebAutocomplete select(WebListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebAutocomplete sendKeys(CharSequence... keys);


    @Override
    WebAutocomplete shouldBePresent();

    @Override
    WebAutocomplete shouldNotBePresent();

    @Override
    WebAutocomplete shouldBeDisplayed();

    @Override
    WebAutocomplete shouldNotBeDisplayed();

    @Override
    WebAutocomplete shouldBeInFocus();

    @Override
    WebAutocomplete shouldNotBeInFocus();

    @Override
    WebAutocomplete shouldBeOpen();

    @Override
    WebAutocomplete shouldBeClosed();


    @Override
    WebAutocomplete shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebAutocomplete shouldHaveText(StringValue stringValue);

    @Override
    WebAutocomplete shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebAutocomplete shouldNotHaveText(StringValue stringValue);

    @Override
    WebAutocomplete shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebAutocomplete shouldHaveLabel(StringValue stringValue);

    @Override
    WebAutocomplete shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebAutocomplete shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebAutocomplete shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebAutocomplete shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebAutocomplete shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebAutocomplete shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebAutocomplete shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebAutocomplete componentShouldBePresent(String componentName);

    @Override
    WebAutocomplete componentShouldNotBePresent(String componentName);

    @Override
    WebAutocomplete componentShouldBeDisplayed(String componentName);

    @Override
    WebAutocomplete componentShouldNotBeDisplayed(String componentName);

    @Override
    WebAutocomplete componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebAutocomplete componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebAutocomplete componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebAutocomplete componentShouldNotLooksLike(String componentName, Screenshot screenshot);

}
