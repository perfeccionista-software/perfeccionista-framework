package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public interface WebTextInput extends WebChildElement,
        ClickAvailable, GetTextAvailable, SendKeysAvailable, ClearAvailable, IsEnabledAvailable, GetLabelAvailable {

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
    WebTextInput shouldBeDisabled();

    @Override
    WebTextInput shouldBeDisplayed();

    @Override
    WebTextInput shouldNotBeDisplayed();

    @Override
    WebTextInput shouldBeEnabled();

    @Override
    WebTextInput shouldHaveBounds(Bounds bounds);

    @Override
    WebTextInput shouldHaveLabel(StringValue stringValue);

    @Override
    WebTextInput shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebTextInput shouldHaveText(StringValue stringValue);

    @Override
    WebTextInput shouldLooksLike(Screenshot screenshot);

    @Override
    WebTextInput stateShouldBeDisplayed(String stateName);

}
