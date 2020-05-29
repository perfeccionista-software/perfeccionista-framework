package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.CLICK_METHOD;

@ElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
// TODO: Сделать для всех элементов
//@WebLocator(component = CLICK, invokeOnCall = {ScrollToFunctionInvoke.class, IsDisplayedFunctionInvoke.class})
//@WebLocator(component = TEXT, invokeOnCall = {ScrollToFunctionInvoke.class, IsDisplayedFunctionInvoke.class})
public abstract class WebLinkSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebLink {

//    @Override
//    public void click() {
//        getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public String getText() {
//        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
//    }

}
