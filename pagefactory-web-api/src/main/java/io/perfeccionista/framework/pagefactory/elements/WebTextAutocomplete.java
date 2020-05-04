package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebTextAutocomplete extends WebTextDropDownList,
        SendKeysAvailable, ClearAvailable {

    @Override
    WebTextAutocomplete executeAction(String name, Object... args);

    @Override
    WebTextAutocomplete executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTextAutocomplete clear();

    @Override
    WebTextAutocomplete click();

    @Override
    WebTextAutocomplete clickToElement(WebTextListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebTextAutocomplete open();

    @Override
    WebTextAutocomplete close();

    @Override
    WebTextAutocomplete hoverTo(boolean withOutOfBounds);

    @Override
    WebTextAutocomplete scrollTo();

    @Override
    WebTextAutocomplete scrollToElement(WebTextListFilter filter);

    @Override
    WebTextAutocomplete sendKeys(CharSequence... keys);


    @Override
    WebTextAutocomplete shouldBePresent();

    @Override
    WebTextAutocomplete shouldNotBePresent();

    @Override
    WebTextAutocomplete shouldBeDisplayed();

    @Override
    WebTextAutocomplete shouldNotBeDisplayed();

    @Override
    WebTextAutocomplete shouldBeInFocus();

    @Override
    WebTextAutocomplete shouldNotBeInFocus();

    @Override
    WebTextAutocomplete shouldBeOpen();

    @Override
    WebTextAutocomplete shouldBeClosed();


    @Override
    WebTextAutocomplete shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebTextAutocomplete shouldHaveText(StringValue stringValue);

    @Override
    WebTextAutocomplete shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebTextAutocomplete shouldNotHaveText(StringValue stringValue);

    @Override
    WebTextAutocomplete shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebTextAutocomplete shouldHaveLabel(StringValue stringValue);

    @Override
    WebTextAutocomplete shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebTextAutocomplete shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebTextAutocomplete shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebTextAutocomplete shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextAutocomplete shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTextAutocomplete shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextAutocomplete shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTextAutocomplete componentShouldBePresent(String componentName);

    @Override
    WebTextAutocomplete componentShouldNotBePresent(String componentName);

    @Override
    WebTextAutocomplete componentShouldBeDisplayed(String componentName);

    @Override
    WebTextAutocomplete componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTextAutocomplete componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextAutocomplete componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextAutocomplete componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTextAutocomplete componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
