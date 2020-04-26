package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileFileInput;


//@AndroidLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
//@IosLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
//@ElementAction(name = CLEAR_METHOD, implementation = AppiumClear.class)
//@ElementAction(name = GET_LABEL_METHOD, implementation = AppiumGetLabel.class)
//@ElementAction(name = GET_TEXT_METHOD, implementation = AppiumGetText.class)
//@ElementAction(name = IS_ENABLED_METHOD, implementation = AppiumIsEnabled.class)
//@ElementAction(name = SEND_KEYS_METHOD, implementation = AppiumSendKeys.class)
//@ElementAction(name = SUBMIT_METHOD, implementation = AppiumSubmit.class)
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
