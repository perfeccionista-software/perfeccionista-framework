package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSelectWebBlock;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClose;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumOpen;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.CLICK_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.CLICK_METHOD;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementAction(name = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementAction(name = OPEN_METHOD, implementation = SeleniumOpen.class)
@ElementAction(name = CLOSE_METHOD, implementation = SeleniumClose.class)
@ElementAction(name = CLICK_TO_ELEMENT_METHOD, implementation = JsSelectWebBlock.class)
public abstract class WebDropDownListSeleniumImpl extends WebListSeleniumImpl implements WebDropDownList {
//
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
//    public String getText() {
//        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
//    }
//
//    @Override
//    public boolean isOpen() {
//        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this, UL);
//    }
//
//    @Override
//    public void open() {
//        getMethodImplementation(OPEN_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public void close() {
//        getMethodImplementation(CLOSE_METHOD, Void.class).execute(this);
//    }

}
