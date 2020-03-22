package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileFileInput;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSubmit;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SUBMIT_METHOD;

@AndroidLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@IosLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLEAR_METHOD, implementation = AppiumClear.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = AppiumGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = AppiumGetText.class)
@ElementMethod(type = IS_ENABLED_METHOD, implementation = AppiumIsEnabled.class)
@ElementMethod(type = SEND_KEYS_METHOD, implementation = AppiumSendKeys.class)
@ElementMethod(type = SUBMIT_METHOD, implementation = AppiumSubmit.class)
public abstract class MobileFileInputImpl extends AbstractMobileChildElement implements MobileFileInput {

//    @Override
//    public OperationResult<Void> clear() {
//        return getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
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
//    public OperationResult<Boolean> isEnabled() {
//        return getMethodImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Void> sendKeys(CharSequence... keys) {
//        return getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }
//
//    @Override
//    public OperationResult<Void> submit() {
//        return getMethodImplementation(SUBMIT_METHOD, Void.class).execute(this);
//    }

}
