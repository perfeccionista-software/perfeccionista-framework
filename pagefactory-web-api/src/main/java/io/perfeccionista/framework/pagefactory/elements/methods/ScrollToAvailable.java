package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_METHOD;

public interface ScrollToAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(SCROLL_TO_METHOD)
    ScrollToAvailable scrollTo();

}
