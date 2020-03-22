package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumClose;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumOpen;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumTap;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.UL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.TAP_METHOD;

@AndroidLocator(component = UL, xpath = ".//ul")
@AndroidLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@IosLocator(component = UL, xpath = ".//ul")
@IosLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = TAP_METHOD, implementation = AppiumTap.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = AppiumGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = AppiumGetText.class)
@ElementMethod(type = OPEN_METHOD, implementation = AppiumOpen.class)
@ElementMethod(type = CLOSE_METHOD, implementation = AppiumClose.class)
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
