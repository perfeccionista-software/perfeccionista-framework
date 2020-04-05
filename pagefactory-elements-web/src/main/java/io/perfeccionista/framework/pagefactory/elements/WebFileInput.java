package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSubmit;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SubmitAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SUBMIT_METHOD;

@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementMethod(type = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
@ElementMethod(type = SUBMIT_METHOD, implementation = SeleniumSubmit.class)
public interface WebFileInput extends WebChildElement,
        GetTextAvailable, SendKeysAvailable, ClearAvailable, SubmitAvailable, IsEnabledAvailable, GetLabelAvailable {

    @Override
    WebFileInput clear();

    @Override
    WebFileInput hoverTo(boolean withOutOfBounds);

    @Override
    WebFileInput scrollTo();

    @Override
    WebFileInput sendKeys(CharSequence... keys);

    @Override
    WebFileInput shouldBeDisabled();

    @Override
    WebFileInput shouldBeDisplayed();

    @Override
    WebFileInput shouldNotBeDisplayed();

    @Override
    WebFileInput shouldBeEnabled();

    @Override
    WebFileInput shouldHaveBounds(Bounds bounds);

    @Override
    WebFileInput shouldHaveLabel(StringValue stringValue);

    @Override
    WebFileInput shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebFileInput shouldHaveText(StringValue stringValue);

    @Override
    WebFileInput shouldLooksLike(Screenshot screenshot);

    @Override
    WebFileInput stateShouldBeDisplayed(String stateName);

    @Override
    WebFileInput submit();

}
