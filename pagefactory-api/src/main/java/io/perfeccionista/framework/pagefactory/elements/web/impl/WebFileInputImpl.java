package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumSubmit;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SUBMIT_METHOD;

@Locator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementMethod(type = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
@ElementMethod(type = SUBMIT_METHOD, implementation = SeleniumSubmit.class)
public class WebFileInputImpl extends AbstractWebChildElement implements WebFileInput {

    @Override
    public OperationResult<Void> clear() {
        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<String> getLabel() {
        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
    }

    @Override
    public OperationResult<String> getText() {
        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
    }

    @Override
    public OperationResult<Boolean> isEnabled() {
        return getMethodImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this);
    }

    @Override
    public OperationResult<Void> sendKeys(CharSequence... keys) {
        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
    }

    @Override
    public OperationResult<Void> submit() {
        return getMethodImplementation(SUBMIT_METHOD, Void.class).execute(this);
    }

}
