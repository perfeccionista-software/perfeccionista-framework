package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileTextInput;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumTap;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.TAP_METHOD;

@ElementMethod(type = CLEAR_METHOD, implementation = AppiumClear.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = AppiumGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = AppiumGetText.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = AppiumSendKeys.class)
@ElementMethod(type = TAP_METHOD, implementation = AppiumTap.class)
public abstract class MobileTextInputImpl extends AbstractMobileChildElement implements MobileTextInput {
//
//    @Override
//    public OperationResult<Void> clear() {
//        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Void> tap() {
//        return getMethodImplementation(TAP_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<String> getLabel() {
//        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<String> getText() {
//        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Void> sendKeys(CharSequence... keys) {
//        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }

}
