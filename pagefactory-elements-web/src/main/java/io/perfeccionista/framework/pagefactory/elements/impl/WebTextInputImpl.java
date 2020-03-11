package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

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
public class WebTextInputImpl extends AbstractWebChildElement implements WebTextInput {

    @Override
    public OperationResult<Void> clear() {
        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<Void> click() {
        return getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
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
    public OperationResult<Void> sendKeys(CharSequence... keys) {
        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
    }

}
