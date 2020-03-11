package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.WebSimpleAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

@ElementMethod(type = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public class WebSimpleAutocompleteImpl extends WebSimpleDropDownListImpl implements WebSimpleAutocomplete {

    @Override
    public OperationResult<Void> clear() {
        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<Void> sendKeys(CharSequence... keys) {
        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
    }

}
