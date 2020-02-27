package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumIsSelected;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_SELECTED_METHOD;

@Locator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@ElementMethod(type = IS_SELECTED_METHOD, implementation = SeleniumIsSelected.class)
public class WebCheckboxImpl extends AbstractWebChildElement implements WebCheckbox {

    @Override
    public OperationResult<Void> click() {
        return getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
    }

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

}
