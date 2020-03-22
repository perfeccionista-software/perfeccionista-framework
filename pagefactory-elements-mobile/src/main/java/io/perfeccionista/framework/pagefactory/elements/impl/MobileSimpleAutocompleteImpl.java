package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.MobileSimpleAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSendKeys;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

@ElementMethod(type = CLEAR_METHOD, implementation = AppiumClear.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = AppiumSendKeys.class)
public abstract class MobileSimpleAutocompleteImpl extends MobileSimpleDropDownListImpl implements MobileSimpleAutocomplete {

//    @Override
//    public OperationResult<Void> clear() {
//        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Void> sendKeys(CharSequence... keys) {
//        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }

}
