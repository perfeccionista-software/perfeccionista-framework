package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileCheckbox;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumIsSelected;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumTap;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.TAP_METHOD;

@AndroidLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@IosLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = GET_LABEL_METHOD, implementation = AppiumGetLabel.class)
@ElementMethod(type = IS_ENABLED_METHOD, implementation = AppiumIsEnabled.class)
@ElementMethod(type = IS_SELECTED_METHOD, implementation = AppiumIsSelected.class)
@ElementMethod(type = TAP_METHOD, implementation = AppiumTap.class)
public class MobileCheckboxImpl extends AbstractMobileChildElement implements MobileCheckbox {

    @Override
    public OperationResult<String> getLabel() {
        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
    }

    @Override
    public OperationResult<Boolean> isEnabled() {
        return getMethodImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this);
    }

    @Override
    public OperationResult<Boolean> isSelected() {
        return getMethodImplementation(IS_SELECTED_METHOD, Boolean.class).execute(this);
    }

    @Override
    public OperationResult<Void> tap() {
        return getMethodImplementation(TAP_METHOD, Void.class).execute(this);
    }

}


