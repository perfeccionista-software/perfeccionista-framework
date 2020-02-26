package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.WebAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public class WebAutocompleteImpl extends WebDropDownListImpl implements WebAutocomplete {

    @Override
    public OperationResult<Void> clear() {
        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<Void> sendKeys(CharSequence... keys) {
        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
    }

}
