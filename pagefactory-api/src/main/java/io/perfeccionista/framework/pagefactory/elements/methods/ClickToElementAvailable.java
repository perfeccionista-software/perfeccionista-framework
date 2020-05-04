package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.filter.Filter;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.CLICK_TO_ELEMENT_METHOD;

public interface ClickToElementAvailable<F extends Filter> extends Element {

    @ActionMethodType
    @MappedElementAction(CLICK_TO_ELEMENT_METHOD)
    ClickToElementAvailable clickToElement(F filter);

}
