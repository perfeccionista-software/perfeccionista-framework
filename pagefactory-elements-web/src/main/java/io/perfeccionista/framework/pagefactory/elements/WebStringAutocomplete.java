package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebStringListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public interface WebStringAutocomplete extends WebStringDropDownList,
        SendKeysAvailable, ClearAvailable {

    WebStringAutocomplete select(WebStringListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebStringAutocomplete clear();

    @Override
    WebStringAutocomplete click();

    @Override
    WebStringAutocomplete open();

    @Override
    WebStringAutocomplete close();

    @Override
    WebStringAutocomplete hoverTo(boolean withOutOfBounds);

    @Override
    WebStringAutocomplete scrollTo();

    @Override
    WebStringAutocomplete scrollToElement(WebStringListFilter filter);

    @Override
    WebStringAutocomplete sendKeys(CharSequence... keys);

    @Override
    WebStringAutocomplete shouldBeDisplayed();

    @Override
    WebStringAutocomplete shouldNotBeDisplayed();

    @Override
    WebStringAutocomplete shouldBeOpen();

    @Override
    WebStringAutocomplete shouldBeClosed();

    @Override
    WebStringAutocomplete shouldHaveBounds(Bounds bounds);

    @Override
    WebStringAutocomplete shouldHaveLabel(StringValue stringValue);

    @Override
    WebStringAutocomplete shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebStringAutocomplete shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebStringAutocomplete shouldHaveText(StringValue stringValue);

    @Override
    WebStringAutocomplete shouldLooksLike(Screenshot screenshot);

    @Override
    WebStringAutocomplete stateShouldBeDisplayed(String stateName);

}
