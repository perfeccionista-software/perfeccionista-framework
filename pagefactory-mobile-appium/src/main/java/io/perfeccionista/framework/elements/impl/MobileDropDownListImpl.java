package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;


//@AndroidLocator(component = UL, xpath = ".//ul")
//@AndroidLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
//@IosLocator(component = UL, xpath = ".//ul")
//@IosLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
//@ElementAction(name = TAP_METHOD, implementation = AppiumTap.class)
//@ElementAction(name = GET_LABEL_METHOD, implementation = AppiumGetLabel.class)
//@ElementAction(name = GET_TEXT_METHOD, implementation = AppiumGetText.class)
//@ElementAction(name = OPEN_METHOD, implementation = AppiumOpen.class)
//@ElementAction(name = CLOSE_METHOD, implementation = AppiumClose.class)
public abstract class MobileDropDownListImpl extends MobileUnorderedListImpl implements MobileDropDownList {

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
//    public OperationResult<Void> tap() {
//        return getMethodImplementation(TAP_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Boolean> isOpen() {
//        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this, UL);
//    }
//
//    @Override
//    public OperationResult<Void> open() {
//        return getMethodImplementation(OPEN_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public OperationResult<Void> close() {
//        return getMethodImplementation(CLOSE_METHOD, Void.class).execute(this);
//    }

}
