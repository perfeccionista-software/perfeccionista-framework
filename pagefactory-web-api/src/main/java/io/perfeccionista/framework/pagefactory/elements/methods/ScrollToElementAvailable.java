package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.filter.Filter;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_ELEMENT_METHOD;

public interface ScrollToElementAvailable<F extends Filter<?, ?>> extends Element {

    @ActionMethodType
    @MappedElementAction(SCROLL_TO_ELEMENT_METHOD)
    ScrollToElementAvailable<F> scrollToElement(F filter);

}
