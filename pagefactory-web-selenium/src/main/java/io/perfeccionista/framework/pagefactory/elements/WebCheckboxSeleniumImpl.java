package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumIsSelected;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.CLICK_METHOD;

@ElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementAction(name = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementAction(name = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@ElementAction(name = IS_SELECTED_METHOD, implementation = SeleniumIsSelected.class)
@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
public abstract class WebCheckboxSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebCheckbox {

//    @Override
//    public void click() {
//        getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public String getLabel() {
//        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return getMethodImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this);
//    }
//
//    @Override
//    public boolean isSelected() {
//        return getMethodImplementation(IS_SELECTED_METHOD, Boolean.class).execute(this);
//    }

}
