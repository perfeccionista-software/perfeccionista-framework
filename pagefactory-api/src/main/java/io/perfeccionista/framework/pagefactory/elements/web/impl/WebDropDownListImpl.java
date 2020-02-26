package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.WebDropDownList;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumClose;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumOpen;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.UL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.OPEN_METHOD;

@Locator(component = UL, xpath = ".//ul")
@Locator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementMethod(type = OPEN_METHOD, implementation = SeleniumOpen.class)
@ElementMethod(type = CLOSE_METHOD, implementation = SeleniumClose.class)
public class WebDropDownListImpl extends WebUnorderedListImpl implements WebDropDownList {

    @Override
    public OperationResult<Void> click() {
        return getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<String> getLabel() {
        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
    }

    @Override
    public OperationResult<String> getText() {
        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
    }

    @Override
    public OperationResult<Boolean> isOpen() {
        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this, UL);
    }

    @Override
    public OperationResult<Void> open() {
        return getMethodImplementation(OPEN_METHOD, Void.class).execute(this);
    }

    @Override
    public OperationResult<Void> close() {
        return getMethodImplementation(CLOSE_METHOD, Void.class).execute(this);
    }

}
