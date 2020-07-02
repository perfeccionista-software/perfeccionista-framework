package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_ELEMENT_METHOD;

public interface ScrollToElementAvailable<F extends WebFilter<?, ?>> extends WebLocatorChainAvailable {

    @ActionMethodType
    @MappedElementAction(SCROLL_TO_ELEMENT_METHOD)
    WebChildElement scrollToElement(F filter);

}
