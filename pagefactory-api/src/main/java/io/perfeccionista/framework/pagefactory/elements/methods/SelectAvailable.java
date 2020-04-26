package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.filter.Filter;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SELECT_METHOD;

public interface SelectAvailable<F extends Filter> extends Element {

    @ActionMethodType
    @MappedElementAction(SELECT_METHOD)
    SelectAvailable select(F filter);

}
