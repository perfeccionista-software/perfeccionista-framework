package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public interface WebAutocomplete extends WebDropDownList,
        SendKeysAvailable, ClearAvailable {

    WebAutocomplete select(WebListFilter filter);

    @Override
    WebAutocomplete clear();

    @Override
    WebAutocomplete click();

    @Override
    WebAutocomplete open();

    @Override
    WebAutocomplete close();

    @Override
    WebAutocomplete sendKeys(CharSequence... keys);

    @Override
    WebAutocomplete hoverTo(boolean withOutOfBounds);

    @Override
    WebAutocomplete scrollTo();

    @Override
    WebAutocomplete scrollToElement(WebListFilter filter);

    @Override
    WebAutocomplete shouldBeOpen();

    @Override
    WebAutocomplete shouldBeClosed();

    @Override
    WebAutocomplete shouldBeDisplayed();

    @Override
    WebAutocomplete shouldNotBeDisplayed();

    @Override
    WebAutocomplete shouldHaveBounds(Bounds bounds);

    @Override
    WebAutocomplete shouldHaveLabel(StringValue stringValue);

    @Override
    WebAutocomplete shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebAutocomplete shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebAutocomplete shouldHaveText(StringValue stringValue);

    @Override
    WebAutocomplete shouldLooksLike(Screenshot screenshot);

    @Override
    WebAutocomplete stateShouldBeDisplayed(String stateName);

}
