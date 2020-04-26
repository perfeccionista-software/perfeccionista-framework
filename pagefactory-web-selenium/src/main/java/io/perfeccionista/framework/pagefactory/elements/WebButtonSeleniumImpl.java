package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.CLICK_METHOD;

@ElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
public abstract class WebButtonSeleniumImpl extends WebLinkSeleniumImpl implements WebButton {
}
